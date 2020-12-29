package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PlayerPositionAndLook : Packet() {
    var x by double()
    var y by double()
    var z by double()
    var yaw by float()
    var pitch by float()
    var flags by byte()
    var teleportId by varInt()
    companion object : PacketMeta<PlayerPositionAndLook>(0x34, ConnectionState.PLAY, Bound.CLIENT, ::PlayerPositionAndLook)
}
