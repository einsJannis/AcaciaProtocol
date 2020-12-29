package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Packet

class OpenHorseWindow : Packet() {
    var windowId by ubyte()
    var numberOfSlots by varInt()
    var entityId by int()
}
