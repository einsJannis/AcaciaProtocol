package dev.einsjannis.acacia.protocol.io.net.server

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.io.ByteArrayReader
import dev.einsjannis.acacia.protocol.readVarInt
import io.ktor.network.sockets.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Client(val socket: Socket) {
    
    val connectionState: ConnectionState = ConnectionState.HANDSHAKE
    
    suspend fun handle() {
        while (!socket.isClosed) {
            val reader = socket.openReadChannel()
            val packetSize = reader.readVarInt()
            val bytes = ByteArray(packetSize)
            reader.readAvailable(bytes, 0, packetSize)
            GlobalScope.launch {
                val packet = parsePacket(bytes)
                TODO("handle packet")
            }
        }
    }
    
    suspend fun parsePacket(bytes: ByteArray): Packet {
        val byteArrayReader = ByteArrayReader(bytes)
        val id = byteArrayReader.readVarInt()
        return Packet.read(id, connectionState, Bound.SERVER, byteArrayReader)
    }
    
}
