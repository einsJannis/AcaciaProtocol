package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityMetadata : Packet() {
    var entityId by varInt()
    var metadata by entityMetadata()
    companion object : PacketMeta<EntityMetadata>(0x44, ConnectionState.PLAY, Bound.CLIENT, ::EntityMetadata)
}
