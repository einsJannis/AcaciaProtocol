package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Effect : Packet() {
    var effectId by int()
    var location by position()
    var data by int()
    var disableRelativeVolume by bool()
    companion object : PacketMeta<Effect>(0x21, ConnectionState.PLAY, Bound.CLIENT, ::Effect)
}
