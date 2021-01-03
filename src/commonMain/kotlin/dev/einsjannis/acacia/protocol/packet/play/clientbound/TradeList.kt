package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.Trade

class TradeList : Packet() {
    var windowId by varInt()
    var size by ubyte()
    var trades by `object`(::Trade).array({ size.toInt() }, { size = it.toUByte() })
    var villagerLevel by varInt()
    var expirience by varInt()
    var isRegularVillager by bool()
    var canRestock by bool()
    companion object : PacketMeta<TradeList>(0x26, ConnectionState.PLAY, Bound.CLIENT, ::TradeList)
}
