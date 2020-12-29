package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class TabComplete : Packet() {
    var transactionId by varInt()
    var text by string()

    companion object : PacketMeta<TabComplete>(0x06, ConnectionState.PLAY, Bound.SERVER, ::TabComplete)
}
