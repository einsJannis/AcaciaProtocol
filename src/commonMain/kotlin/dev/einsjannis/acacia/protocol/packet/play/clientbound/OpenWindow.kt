package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class OpenWindow : Packet() {
    var windowId by varInt()
    var windowType by varInt()
    var windowTitle by chat()
    companion object : PacketMeta<OpenWindow>(0x2D, ConnectionState.PLAY, Bound.CLIENT, ::OpenWindow)
}
