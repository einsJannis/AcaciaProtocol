package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.bitFlag
import dev.einsjannis.acacia.protocol.primitives.FlagBit
import dev.einsjannis.acacia.protocol.primitives.Flags

class PlayerAbilityFlags(value: Int): Flags(value){
    var invulnerable by FlagBit(0)
    var flying by FlagBit(1)
    var allowFlying by FlagBit(2)
    var creativeMode by FlagBit(3)
}
class PlayerAbilities : Packet() {
    var flags by byte().mapped({it.toInt()}, {it.toByte()}).bitFlag(::PlayerAbilityFlags)
    var flyingSpeed by float()
    var fieldOfViewMod by float()
    companion object : PacketMeta<PlayerAbilities>(0x30, ConnectionState.PLAY, Bound.CLIENT, ::PlayerAbilities)
}
