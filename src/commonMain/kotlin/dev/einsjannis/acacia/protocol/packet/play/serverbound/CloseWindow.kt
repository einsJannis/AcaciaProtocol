package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class CloseWindow : Packet() {
    var windowId by ubyte()

    companion object : PacketMeta<CloseWindow>(0x0A, ConnectionState.PLAY, Bound.SERVER, ::CloseWindow)
}
