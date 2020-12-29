package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class HeldItemChange : Packet() {
    var itemSlot by byte()
    companion object : PacketMeta<HeldItemChange>(0x3F, ConnectionState.PLAY, Bound.CLIENT, ::HeldItemChange)
}
