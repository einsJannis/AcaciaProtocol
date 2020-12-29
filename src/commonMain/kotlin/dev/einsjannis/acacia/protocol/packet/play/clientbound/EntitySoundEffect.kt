package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*

class EntitySoundEffect : Packet() {
    var soundId by varInt()
    var soundCategory by varInt().mapped({ SoundCategory.values()[it] }, { it.ordinal })
    var entityId by varInt()
    var volume by float()
    var pitch by float()
    companion object : PacketMeta<EntitySoundEffect>(0x50, ConnectionState.PLAY, Bound.CLIENT, ::EntitySoundEffect)
}
