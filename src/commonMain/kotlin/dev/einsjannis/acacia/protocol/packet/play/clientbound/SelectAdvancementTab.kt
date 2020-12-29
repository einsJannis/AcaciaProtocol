package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SelectAdvancementTab : Packet() {
    var hasId by bool()
    var identifier by string().onlyIf(::hasId)
    companion object : PacketMeta<SelectAdvancementTab>(0x3C, ConnectionState.PLAY, Bound.CLIENT, ::SelectAdvancementTab)
}
