package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.statistic.Statistic

class Statistics : Packet() {
    var count by varInt()
    var statistics by `object`(::Statistic).array(::count)
    companion object : PacketMeta<Statistics>(0x06, ConnectionState.PLAY, Bound.CLIENT, ::Statistics)
}
