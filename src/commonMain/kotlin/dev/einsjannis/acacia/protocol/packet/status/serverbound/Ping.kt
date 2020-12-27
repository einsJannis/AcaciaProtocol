package dev.einsjannis.acacia.protocol.packet.status.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Ping : Packet() {
    val payload by long()
    companion object : PacketMeta<Ping>(0x01, ConnectionState.STATUS, Bound.SERVER, ::Ping)
}
