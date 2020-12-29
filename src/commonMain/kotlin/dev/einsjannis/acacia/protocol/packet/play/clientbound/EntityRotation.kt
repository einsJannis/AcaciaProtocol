package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityRotation : Packet() {
    var entityId by varInt()
    var yaw by angle()
    var pitch by angle()
    var onGround by bool()
    companion object : PacketMeta<EntityRotation>(0x29, ConnectionState.PLAY, Bound.CLIENT, ::EntityRotation)
}
