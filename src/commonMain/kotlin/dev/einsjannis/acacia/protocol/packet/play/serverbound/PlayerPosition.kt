package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PlayerPosition : Packet() {
    var x by double()
    var yFeet by double()
    var z by double()
    var onGround by bool()

    companion object : PacketMeta<PlayerPosition>(0x12, ConnectionState.PLAY, Bound.SERVER, ::PlayerPosition)
}
