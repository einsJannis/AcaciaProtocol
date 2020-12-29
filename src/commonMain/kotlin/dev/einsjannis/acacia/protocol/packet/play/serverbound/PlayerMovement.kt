package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PlayerMovement : Packet() {
    var onGround by bool()

    companion object : PacketMeta<PlayerMovement>(0x15, ConnectionState.PLAY, Bound.SERVER, ::PlayerMovement)
}
