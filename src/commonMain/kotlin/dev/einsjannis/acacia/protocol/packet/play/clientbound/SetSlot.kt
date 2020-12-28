package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SetSlot : Packet() {
    var windowId by ubyte()
    var slotIndex by short()
    var slotData by slot()
    companion object : PacketMeta<SetSlot>(0x15, ConnectionState.PLAY, Bound.CLIENT, ::SetSlot)
}
