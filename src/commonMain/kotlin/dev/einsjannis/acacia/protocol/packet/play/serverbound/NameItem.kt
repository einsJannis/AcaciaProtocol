package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class NameItem : Packet() {
    var itemName by string()

    companion object : PacketMeta<NameItem>(0x20, ConnectionState.PLAY, Bound.SERVER, ::NameItem)
}
