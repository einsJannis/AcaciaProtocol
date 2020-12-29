package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.enumOrdinalMapping

enum class EntityActionType {
    START_SNEAKING,
    STOP_SNEAKING,
    LEAVE_BED,
    START_SPRINTING,
    STOP_SPRINTING,
    START_JUMP_WITH_HORSE,
    STOP_JUMP_WITH_HORSE,
    OPEN_HORSE_INVENTORY,
    START_FLYING_WITH_ELYTRA,
}

class EntityAction : Packet() {
    var entityId by varInt()
    var actionId by varInt().enumOrdinalMapping<EntityActionType>()
    var jumpBoost by varInt()

    companion object : PacketMeta<EntityAction>(0x1C, ConnectionState.PLAY, Bound.SERVER, ::EntityAction)
}
