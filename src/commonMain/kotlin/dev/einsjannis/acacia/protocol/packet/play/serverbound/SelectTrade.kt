package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SelectTrade : Packet() {
    var selectedSlot by varInt()

    companion object : PacketMeta<SelectTrade>(0x23, ConnectionState.PLAY, Bound.SERVER, ::SelectTrade)
}
