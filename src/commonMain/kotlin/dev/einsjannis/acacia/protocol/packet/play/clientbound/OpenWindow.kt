package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Packet

class OpenWindow : Packet() {
    var windowId by varInt()
    var windowType by varInt()
    var windowTitle by chat()
}
