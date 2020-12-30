package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class HeldItemChange : Packet() {
    var selectedSlot by short()

    companion object : PacketMeta<HeldItemChange>(0x25, ConnectionState.PLAY, Bound.SERVER, ::HeldItemChange)
}
