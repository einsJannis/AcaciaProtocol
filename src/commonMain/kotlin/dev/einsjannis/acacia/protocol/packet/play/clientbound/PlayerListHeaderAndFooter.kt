package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PlayerListHeaderAndFooter : Packet() {
    var header by chat()
    var footer by chat()
    companion object : PacketMeta<PlayerListHeaderAndFooter>(0x54, ConnectionState.PLAY, Bound.CLIENT, ::PlayerListHeaderAndFooter)
}
