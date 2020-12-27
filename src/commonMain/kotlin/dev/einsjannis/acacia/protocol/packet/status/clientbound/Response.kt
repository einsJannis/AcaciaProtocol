package dev.einsjannis.acacia.protocol.packet.status.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Response : Packet() {
    val jsonResponse by string()
    companion object : PacketMeta<Response>(0x00, ConnectionState.HANDSHAKE, Bound.CLIENT, ::Response)
}
