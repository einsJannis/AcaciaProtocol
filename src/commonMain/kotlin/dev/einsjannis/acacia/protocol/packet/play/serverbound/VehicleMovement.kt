package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class VehicleMovement : Packet() {
    var x by double()
    var y by double()
    var z by double()
    var yaw by float()
    var pitch by float()

    companion object : PacketMeta<VehicleMovement>(0x16, ConnectionState.PLAY, Bound.SERVER, ::VehicleMovement)
}
