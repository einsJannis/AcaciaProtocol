package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityMovement : Packet() {
    var entityId by varInt()
    companion object : PacketMeta<EntityMovement>(0x2A, ConnectionState.PLAY, Bound.CLIENT, ::EntityMovement)
}
