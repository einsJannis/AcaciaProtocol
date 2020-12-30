package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Spectate : Packet() {
    var target by uuid()

    companion object : PacketMeta<Spectate>(0x2D, ConnectionState.PLAY, Bound.SERVER, ::Spectate)
}
