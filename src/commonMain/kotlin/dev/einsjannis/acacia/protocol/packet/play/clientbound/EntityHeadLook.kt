package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityHeadLook : Packet() {
    var entityId by varInt()
    var headYaw by angle()
    companion object : PacketMeta<EntityHeadLook>(0x3A, ConnectionState.PLAY, Bound.CLIENT, ::EntityHeadLook)
}
