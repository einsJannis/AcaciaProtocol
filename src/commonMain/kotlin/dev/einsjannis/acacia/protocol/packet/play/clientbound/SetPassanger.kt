package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SetPassanger : Packet() {
    var entityId by varInt()
    var passangerCount by varInt()
    var passangers by varInt().array(::passangerCount)
    companion object : PacketMeta<SetPassanger>(0x4C, ConnectionState.PLAY, Bound.CLIENT, ::SetPassanger)
}
