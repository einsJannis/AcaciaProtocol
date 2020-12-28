package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class WindowItems : Packet() {
    var windowId by ubyte()
    var count by short().mapped({ it.toInt() }, { it.toShort() })
    var slotData by slot().array(::count)
    companion object : PacketMeta<WindowItems>(0, ConnectionState.PLAY, Bound.CLIENT, ::WindowItems)
}
