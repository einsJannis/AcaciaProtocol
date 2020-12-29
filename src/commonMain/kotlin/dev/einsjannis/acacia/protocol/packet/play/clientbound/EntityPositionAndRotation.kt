package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityPositionAndRotation : Packet() {
    var entityId by varInt()
    var deltaX by short()
    var deltaY by short()
    var deltaZ by short()
    var yaw by angle()
    var pitch by angle()
    var onGround by bool()
    companion object : PacketMeta<EntityPositionAndRotation>(0x28, ConnectionState.PLAY, Bound.CLIENT, ::EntityPositionAndRotation)
}
