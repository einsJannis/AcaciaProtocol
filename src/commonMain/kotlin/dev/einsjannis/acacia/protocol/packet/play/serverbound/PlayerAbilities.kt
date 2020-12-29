package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.PlayerAbilityFlags

class PlayerAbilities : Packet() {
    var flags by byte().mapped({ PlayerAbilityFlags(it.toInt()) }, { it.value.toByte() })

    companion object : PacketMeta<PlayerAbilities>(0x1A, ConnectionState.PLAY, Bound.SERVER, ::PlayerAbilities)
}
