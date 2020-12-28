package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class CloseWindow : Packet() {
    var windowId by ubyte()
    companion object : PacketMeta<CloseWindow>(0x12, ConnectionState.PLAY, Bound.CLIENT, ::CloseWindow)
}
