package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.Equipment

class EntityEquipment : Packet() {
    var entityId by varInt()
    var equipment by `object`(::Equipment).array(TODO())
    companion object : PacketMeta<EntityEquipment>(0x47, ConnectionState.PLAY, Bound.CLIENT, ::EntityEquipment)
}
