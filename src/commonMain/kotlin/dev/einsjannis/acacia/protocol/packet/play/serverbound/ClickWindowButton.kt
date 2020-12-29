package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class ClickWindowButton : Packet() {
    var windowId by byte()
    var buttonId by byte()
    companion object : PacketMeta<ClickWindowButton>(0x08, ConnectionState.PLAY, Bound.SERVER, ::ClickWindowButton)
}
