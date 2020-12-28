package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class WindowConfirmation : Packet() {
    var windowId by ubyte()
    var actionNumber by short()
    var accepted by bool()
    companion object : PacketMeta<WindowConfirmation>(0x11, ConnectionState.PLAY, Bound.CLIENT, ::WindowConfirmation)
}
