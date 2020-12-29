package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PlayerRotation : Packet() {
    var yaw by float()
    var pitch by float()
    var onGround by bool()

    companion object : PacketMeta<PlayerRotation>(0x14, ConnectionState.PLAY, Bound.SERVER, ::PlayerRotation)
}
