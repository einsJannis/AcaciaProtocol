package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*

class EntityAnimation : Packet() {
    var entityId by varInt()
    var animation by ubyte().mapped({ Animation.values()[it.toInt()] }, { it.ordinal.toUByte() })
    companion object : PacketMeta<EntityAnimation>(0x05, ConnectionState.PLAY, Bound.CLIENT, ::EntityAnimation)
}
