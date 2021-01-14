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
import dev.einsjannis.acacia.protocol.primitives.chat.StringComponent
import dev.einsjannis.runMultiplatformBlocking
import dev.einsjannis.zlib.ZlibWrapper
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.isClosed
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
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

    open fun shutdownGracefully(sendDisconnect: Boolean = false, disconnectMessage: String = "Server shut down") {
        if (state != State.RUNNING) return
        scope.runMultiplatformBlocking {
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

    suspend fun handleInbound() {
        val reader = socket.openReadChannel()
        while (!socket.isClosed && state == State.RUNNING) {
            val packetByteArray = ByteArray(3)
            val packetSizeLength = reader.readAvailable(packetByteArray, 0, 3)
            val sizeReader = ByteArrayReader(packetByteArray.copyOfRange(0, packetSizeLength))
            val packetSize = sizeReader.readVarInt()
            val bytes = ByteArray(packetSize)
            reader.readAvailable(bytes, sizeReader.remainingBytes, packetSize - sizeReader.remainingBytes)
            sizeReader.data.copyInto(bytes, 0, sizeReader.index)
            val byteReader = ByteArrayReader(bytes)
            val packet = if (compressionThreshold > 0) {
                val dataLength = byteReader.readVarInt()
                if (dataLength == 0) {
                    decodePacket(byteReader.readByteArray(byteReader.remainingBytes))
                } else {
                    if (dataLength < compressionThreshold && closeOnUnnecessaryCompression) {
                        shutdownGracefully()
                        throw UnnecessaryCompressionException(compressionThreshold, dataLength, bound)
                    }
                    val decompressedBytes = ZlibWrapper.uncompress(byteReader.readByteArray(byteReader.remainingBytes))
                    if (decompressedBytes.size != dataLength) throw UnexpectedDecompressedLengthException(dataLength, decompressedBytes.size, bytes, decompressedBytes)
                    decodePacket(decompressedBytes)
                }
            } else decodePacket(bytes)
            distributeInboundPacket(packet)
        }
    }

    protected open suspend fun distributeInboundPacket(packet: Packet) {
        inboundChannel.send(packet)
    }

    suspend fun handleOutbound() {
        val writer = socket.openWriteChannel(autoFlush = false)
        for (data in outboundChannel) {
            if (socket.isClosed) return
            val uncompressedBytes = encodePacket(data)
            val toWrite = if (compressionThreshold > 0) {
                ByteArrayWriter().also { baw ->
                    baw.writeVarInt(uncompressedBytes.size)
                    if (uncompressedBytes.size > compressionThreshold) {
                        val compressedBytes = ZlibWrapper.compress(uncompressedBytes.result)
                        baw.writeByteArray(compressedBytes)
                    } else {
                        baw.writeByteArray(uncompressedBytes._result, 0, uncompressedBytes.size)
                    }
                }
            } else {
                uncompressedBytes
            }
            val sizeWriter = ByteArrayWriter(3)
            sizeWriter.writeVarInt(toWrite.size)
            writer.writeAvailable(sizeWriter._result, 0, sizeWriter.size)
            writer.writeAvailable(toWrite._result, 0, toWrite.size)
            writer.flush()
        }
    }

    suspend inline fun <reified T : Packet> send(packet: T) {
        if (state != State.RUNNING) throw NoConnectionException(this)
        outboundChannel.send(PacketWithMeta(packet, Packet.packetMeta()))
    }

    suspend fun decodePacket(bytes: ByteArray): Packet {
        val byteArrayReader = ByteArrayReader(bytes)
        val id = byteArrayReader.readVarInt()
        return Packet.read(id, connectionState, bound, byteArrayReader)
    }

    suspend fun <T : Packet> encodePacket(packet: PacketWithMeta<T>): ByteArrayWriter {
        val byteArrayWriter = ByteArrayWriter()
        byteArrayWriter.writeVarInt(packet.meta.id)
        packet.meta.writePacket(byteArrayWriter, packet.p)
        return byteArrayWriter
    }

}

class ServerClient<DATA>(scope: CoroutineScope, socket: Socket, val server: Server<DATA>, val data: DATA) : Client(scope, socket) {
    override val bound: Bound = Bound.SERVER
    override fun shutdownGracefully(sendDisconnect: Boolean, disconnectMessage: String) {
        server.connectedClients.remove(this)
        super.shutdownGracefully(sendDisconnect, disconnectMessage)
    }
    override suspend fun distributeInboundPacket(packet: Packet) {
        super.distributeInboundPacket(packet)
        server.incomingPackets.send(ClientIncomingPackage(this, packet))
    }
}
