package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class TimeUpdate : Packet() {
    var worldAge by long()
    var timeOfDay by long()
    companion object : PacketMeta<TimeUpdate>(0x4E, ConnectionState.PLAY, Bound.CLIENT, ::TimeUpdate)
}
