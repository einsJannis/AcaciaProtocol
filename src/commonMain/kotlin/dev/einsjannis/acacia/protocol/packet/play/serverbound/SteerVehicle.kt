package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.FlagBit
import dev.einsjannis.acacia.protocol.Flags
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.bitFlag


class SteerVehicleFlags(value: Int) : Flags(value) {
    var jump by FlagBit(0)
    var unmount by FlagBit(1)
}

class SteerVehicle : Packet() {
    var sidewards by float()
    var forward by float()
    var flags by varInt().bitFlag(::SteerVehicleFlags)

    companion object : PacketMeta<SteerVehicle>(0x1D, ConnectionState.PLAY, Bound.SERVER, ::SteerVehicle)
}
