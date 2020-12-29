package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityVelocity : Packet() {
    var entityId by varInt()
    var velocityX by short()
    var velocityY by short()
    var velocityZ by short()
    companion object : PacketMeta<EntityVelocity>(0x46, ConnectionState.PLAY, Bound.CLIENT, ::EntityVelocity)
}
