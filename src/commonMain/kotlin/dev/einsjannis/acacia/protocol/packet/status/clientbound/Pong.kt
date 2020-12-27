package dev.einsjannis.acacia.protocol.packet.status.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Pong : Packet() {
    val payload by long()
    companion object : PacketMeta<Pong>(0x01, ConnectionState.STATUS, Bound.CLIENT, ::Pong)
}
