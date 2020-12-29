package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class ClickWindow : Packet() {
    var windowId by ubyte()
    var clickedSlot by short()
    var button by byte()
    var actionNumber by short()
    var mode by varInt()
    var clickedItem by slot()

    companion object : PacketMeta<ClickWindow>(0x09, ConnectionState.PLAY, Bound.SERVER, ::ClickWindow)
}
