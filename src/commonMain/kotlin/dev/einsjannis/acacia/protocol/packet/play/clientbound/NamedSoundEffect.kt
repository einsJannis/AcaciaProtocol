package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.SoundCategory

class NamedSoundEffect : Packet() {
    var soundName by id()
    var soundCategory by varInt().mapped({ SoundCategory.values()[it] }, { it.ordinal })
    var effectPositionX by int()
    var effectPositionY by int()
    var volume by float()
    var pitch by float()
    companion object : PacketMeta<NamedSoundEffect>(0x18, ConnectionState.PLAY, Bound.CLIENT, ::NamedSoundEffect)
}
