package dev.einsjannis.acacia.protocol.io.net

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.io.ByteArrayReader
import dev.einsjannis.acacia.protocol.io.ByteArrayWriter
import dev.einsjannis.acacia.protocol.packet.login.clientbound.Disconnect as LoginDisconnect
import dev.einsjannis.acacia.protocol.packet.play.clientbound.Disconnect as PlayDisconnect
import dev.einsjannis.acacia.protocol.primitives.chat.ChatComponent
import dev.einsjannis.acacia.protocol.primitives.chat.StringComponent
import io.ktor.network.sockets.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

data class PacketWithMeta<T : Packet>(val p: T, val meta: PacketMeta<T>)

class Client(val scope: CoroutineScope, val socket: Socket, val bound: Bound) {
    enum class State {
        RUNNING,
        CLOSING,
        CLOSED
    }

    var connectionState: ConnectionState = ConnectionState.HANDSHAKE

    var state = State.RUNNING

    val inboundChannel = Channel<Packet>(Channel.UNLIMITED)
    val outboundChannel = Channel<PacketWithMeta<*>>(Channel.UNLIMITED)

    var inboundJob: Job? = null
    var outboundJob: Job? = null

    fun run() {
        inboundJob = scope.launch { handleInbound() }
        outboundJob = scope.launch { handleOutbound() }
    }

    fun shutdownGracefully(sendDisconnect: Boolean = false) {
        if (state != State.RUNNING) return
        state = State.CLOSING
        scope.launch {
            if (sendDisconnect && bound == Bound.SERVER) {
                val reason = StringComponent("Server shut down")
                if (connectionState == ConnectionState.LOGIN) send(LoginDisconnect().also { it.reason = reason })
                if (connectionState == ConnectionState.PLAY) send(PlayDisconnect().also { it.reason = reason })
            }
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
            val packetSize = reader.readVarInt()
            val bytes = ByteArray(packetSize)
            reader.readAvailable(bytes, 0, packetSize)
            val packet = decodePacket(bytes)
            inboundChannel.send(packet)
        }
    }

    suspend fun handleOutbound() {
        val writer = socket.openWriteChannel(autoFlush = false)
        for (data in outboundChannel) {
            if (socket.isClosed) return
            val bytes = encodePacket(data)
            writer.writeVarInt(bytes.size)
            writer.writeAvailable(bytes._result, 0, bytes.size)
            writer.flush()
        }
    }

    suspend inline fun <reified T : Packet> send(packet: T) {
        if (state != State.RUNNING) throw TODO()
        outboundChannel.send(PacketWithMeta(packet, Packet.packetMeta()))
    }

    suspend fun decodePacket(bytes: ByteArray): Packet {
        val byteArrayReader = ByteArrayReader(bytes)
        val id = byteArrayReader.readVarInt()
        return Packet.read(id, connectionState, Bound.SERVER, byteArrayReader)
    }

    suspend fun <T : Packet> encodePacket(packet: PacketWithMeta<T>): ByteArrayWriter {
        val byteArrayWriter = ByteArrayWriter()
        byteArrayWriter.writeVarInt(packet.meta.id)
        packet.meta.writePacket(byteArrayWriter, packet.p)
        return byteArrayWriter
    }

}
