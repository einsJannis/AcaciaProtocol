package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityStatus : Packet() {
    var entityId by id()
    var entityStatus by byte()
    companion object : PacketMeta<EntityStatus>(0x1A, ConnectionState.PLAY, Bound.CLIENT, ::EntityStatus)
}
