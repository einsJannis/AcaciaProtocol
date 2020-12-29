package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PlayerAbilities : Packet() {
    var flags by byte()
    var flyingSpeed by float()
    var fieldOfViewMod by float()
    companion object : PacketMeta<PlayerAbilities>(0x30, ConnectionState.PLAY, Bound.CLIENT, ::PlayerAbilities)
}
