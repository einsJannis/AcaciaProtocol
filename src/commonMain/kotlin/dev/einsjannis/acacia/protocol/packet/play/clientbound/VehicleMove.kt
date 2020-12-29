package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class VehicleMove : Packet() {
    var x by double()
    var y by double()
    var z by double()
    var yaw by float()
    var pitch by float()
    companion object : PacketMeta<VehicleMove>(0x2B, ConnectionState.PLAY, Bound.CLIENT, ::VehicleMove)
}
