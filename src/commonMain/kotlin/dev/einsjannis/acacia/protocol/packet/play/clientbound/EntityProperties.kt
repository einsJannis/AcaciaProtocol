package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.EntityProperty

class EntityProperties : Packet() {
    var entityId by varInt()
    var propertiesCount by int()
    var properties by `object`(::EntityProperty).array(::propertiesCount)
    companion object : PacketMeta<EntityProperties>(0x58, ConnectionState.PLAY, Bound.CLIENT, ::EntityProperties)
}
