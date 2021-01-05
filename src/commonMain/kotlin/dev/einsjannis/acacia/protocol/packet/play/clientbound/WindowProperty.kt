package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class WindowProperty : Packet() {
    var windowId by ubyte()
    var property by short()
    var value by short()
    companion object : PacketMeta<WindowProperty>(0x14, ConnectionState.PLAY, Bound.CLIENT, ::WindowProperty)
}
