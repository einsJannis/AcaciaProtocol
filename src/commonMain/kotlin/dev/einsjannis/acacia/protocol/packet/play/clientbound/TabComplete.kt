package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.TabCompleteMatch

class TabComplete : Packet() {
    var transactionId by varInt()
    var start by varInt()
    var length by varInt()
    var count by varInt()
    var matches by `object`(::TabCompleteMatch).array(::count)
    companion object : PacketMeta<TabComplete>(0x0F, ConnectionState.PLAY, Bound.CLIENT, ::TabComplete)
}
