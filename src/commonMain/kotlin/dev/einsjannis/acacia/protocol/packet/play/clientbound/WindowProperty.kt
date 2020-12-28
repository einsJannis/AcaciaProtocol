package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Packet

class WindowProperty : Packet() {
    var windowId by ubyte()
    var property by short()
    var value by short()
}
