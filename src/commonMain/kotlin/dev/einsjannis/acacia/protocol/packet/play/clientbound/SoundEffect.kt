package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.SoundCategory

class SoundEffect : Packet() {
    var soundId by varInt()
    var soundCategory by varInt().mapped({ SoundCategory.values()[it] }, { it.ordinal })
    var effectPositionX by int()
    var effectPositionY by int()
    var effectPositionZ by int()
    var volume by float()
    var pitch by float()
    companion object : PacketMeta<SoundEffect>(0x51, ConnectionState.PLAY, Bound.CLIENT, ::SoundEffect)
}
