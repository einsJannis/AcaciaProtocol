package dev.einsjannis.acacia.protocol.io.net

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.build
import dev.einsjannis.acacia.protocol.exception.NoConnectionException
import dev.einsjannis.acacia.protocol.exception.UnexpectedDecompressedLengthException
import dev.einsjannis.acacia.protocol.exception.UnnecessaryCompressionException
import dev.einsjannis.acacia.protocol.io.ByteArrayReader
import dev.einsjannis.acacia.protocol.io.ByteArrayWriter
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.primitives.chat.StringComponent
import dev.einsjannis.zlib.ZlibWrapper
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.isClosed
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import dev.einsjannis.acacia.protocol.packet.login.clientbound.Disconnect as LoginDisconnect
import dev.einsjannis.acacia.protocol.packet.play.clientbound.Disconnect as PlayDisconnect

data class PacketWithMeta<T : Packet>(val p: T, val meta: PacketMeta<T>)

open class Client(val scope: CoroutineScope, val socket: Socket) {

    enum class State {
        RUNNING,
        CLOSING,
        CLOSED
    }

    open val bound = Bound.CLIENT

    var closeOnUnnecessaryCompression: Boolean = false
    var connectionState: ConnectionState = ConnectionState.HANDSHAKE

    var state = State.RUNNING
    var compressionThreshold: Int = -1

    val inboundChannel = Channel<Packet>(Channel.UNLIMITED)
    val outboundChannel = Channel<PacketWithMeta<*>>(Channel.UNLIMITED)

    var inboundJob: Job? = null
    var outboundJob: Job? = null

    fun run() {
        inboundJob = scope.launch { handleInbound() }
        outboundJob = scope.launch { handleOutbound() }
    }

    open fun shutdownGracefully(sendDisconnect: Boolean = false, disconnectMessage: String = "Server shut down"): Job {
        if (state != State.RUNNING) return scope.async { }
        return scope.async {
            if (sendDisconnect && bound == Bound.SERVER) {
                val reason = StringComponent(disconnectMessage)
                if (connectionState == ConnectionState.LOGIN) send(LoginDisconnect.build { this.reason = reason })
                if (connectionState == ConnectionState.PLAY) send(PlayDisconnect.build { this.reason = reason })
            }
            state = State.CLOSING
            outboundChannel.close()
            inboundJob?.join()
            outboundJob?.join()
            state = State.CLOSED
            socket.close()
        }
    }
    
    private suspend fun decryptIncoming(byteReadChannel: ByteReadChannel): ByteArrayReader {
        val buffer = ByteArray(byteReadChannel.availableForRead)
        byteReadChannel.readAvailable(buffer)
        return ByteArrayReader(buffer)
    }
    
    private suspend fun frameIncoming(reader: PrimitiveReader): ByteArray {
        val packetSize = reader.readVarInt()
        return reader.readByteArray(packetSize)
    }
    
    private suspend fun decompressIncoming(dataLength: Int, framedByteReader: ByteArrayReader): ByteArray {
        if (dataLength < compressionThreshold && closeOnUnnecessaryCompression) {
            shutdownGracefully()
            throw UnnecessaryCompressionException(compressionThreshold, dataLength, bound)
        }
        val decompressedBytes = ZlibWrapper.uncompress(framedByteReader.readByteArray(framedByteReader.remainingBytes))
        if (decompressedBytes.size != dataLength) throw UnexpectedDecompressedLengthException(dataLength, decompressedBytes.size, framedByteReader.data, decompressedBytes)
        return decompressedBytes
    }
    
    private suspend fun decodePacket(bytes: ByteArray): Packet {
        val byteArrayReader = ByteArrayReader(bytes)
        val id = byteArrayReader.readVarInt()
        return Packet.read(id, connectionState, bound, byteArrayReader)
    }

    private suspend fun handleInbound() {
        val byteReadChannel = socket.openReadChannel()
        while (!socket.isClosed && state == State.RUNNING) {
            val reader = decryptIncoming(byteReadChannel)
            while (reader.remainingBytes >= 2) {
                val bytes = frameIncoming(reader)
                val packet = if (compressionThreshold > 0) {
                    val framedByteReader = ByteArrayReader(bytes)
                    val dataLength = framedByteReader.readVarInt()
                    if (dataLength == 0) {
                        decodePacket(framedByteReader.readByteArray(framedByteReader.remainingBytes))
                    } else {
                        decodePacket(decompressIncoming(dataLength, framedByteReader))
                    }
                } else decodePacket(bytes)
                distributeInboundPacket(packet)
            }
        }
    }

    protected open suspend fun distributeInboundPacket(packet: Packet) {
        inboundChannel.send(packet)
    }

    private suspend fun <T : Packet> encodePacket(packet: PacketWithMeta<T>): ByteArrayWriter {
        val byteArrayWriter = ByteArrayWriter()
        byteArrayWriter.writeVarInt(packet.meta.id)
        packet.meta.writePacket(byteArrayWriter, packet.p)
        return byteArrayWriter
    }

    private suspend fun compressOutgoing(uncompressedBytes: ByteArrayWriter) = ByteArrayWriter().also { baw ->
        baw.writeVarInt(uncompressedBytes.size)
        if (uncompressedBytes.size > compressionThreshold) {
            val compressedBytes = ZlibWrapper.compress(uncompressedBytes.result)
            baw.writeByteArray(compressedBytes)
        } else {
            baw.writeByteArray(uncompressedBytes._result, 0, uncompressedBytes.size)
        }
    }

    private suspend fun frameOutgoing(toWrite: ByteArrayWriter): ByteArrayWriter {
        val writer = ByteArrayWriter(3)
        writer.writeVarInt(toWrite.size)
        writer.writeByteArray(toWrite._result, 0, toWrite.size)
        return writer
    }
    
    private suspend fun encryptOutgoing(toEncrypt: ByteArrayWriter): ByteArray {
        return toEncrypt.result
    }

    private suspend fun handleOutbound() {
        val writer = socket.openWriteChannel(autoFlush = false)
        for (data in outboundChannel) {
            if (socket.isClosed) return
            val uncompressedBytes = encodePacket(data)
            val toWrite = if (compressionThreshold > 0) compressOutgoing(uncompressedBytes) else uncompressedBytes
            val toEncrypt = frameOutgoing(toWrite)
            val encrypted = encryptOutgoing(toEncrypt)
            writer.writeAvailable(encrypted)
            writer.flush()
        }
    }

    suspend inline fun <reified T : Packet> send(packet: T) {
        if (state != State.RUNNING) throw NoConnectionException(this)
        outboundChannel.send(PacketWithMeta(packet, Packet.packetMeta()))
    }

}

class ServerClient<DATA>(scope: CoroutineScope, socket: Socket, val server: Server<DATA>, val data: DATA) : Client(scope, socket) {

    override val bound: Bound = Bound.SERVER

    override fun shutdownGracefully(sendDisconnect: Boolean, disconnectMessage: String): Job {
        server.connectedClients.remove(this)
        return super.shutdownGracefully(sendDisconnect, disconnectMessage)
    }

    override suspend fun distributeInboundPacket(packet: Packet) {
        super.distributeInboundPacket(packet)
        server.incomingPackets.send(ClientIncomingPackage(this, packet))
    }

}
