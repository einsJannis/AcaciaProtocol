package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PlayerPositionAndRotation : Packet() {
    var x by double()
    var yFeet by double()
    var z by double()
    var yaw by float()
    var pitch by float()
    var onGround by bool()

    companion object :
        PacketMeta<PlayerPositionAndRotation>(0x13, ConnectionState.PLAY, Bound.SERVER, ::PlayerPositionAndRotation)
}
