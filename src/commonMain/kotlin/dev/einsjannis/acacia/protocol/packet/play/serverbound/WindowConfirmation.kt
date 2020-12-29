package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class WindowConfirmation : Packet() {
    var windowId by byte()
    var actionNumber by short()
    var accepted by bool()

    companion object : PacketMeta<WindowConfirmation>(0x07, ConnectionState.PLAY, Bound.SERVER, ::WindowConfirmation)
}
