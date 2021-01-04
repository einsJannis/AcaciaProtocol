package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class OpenHorseWindow : Packet() {
    var windowId by ubyte()
    var numberOfSlots by varInt()
    var entityId by int()
    companion object : PacketMeta<OpenHorseWindow>(0x1E, ConnectionState.PLAY, Bound.CLIENT, ::OpenHorseWindow)
}
