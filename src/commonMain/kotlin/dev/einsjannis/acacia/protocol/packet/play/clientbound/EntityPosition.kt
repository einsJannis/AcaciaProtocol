package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityPosition : Packet() {
    var entityId by varInt()
    var deltaX by short()
    var deltaY by short()
    var deltaZ by short()
    var onGround by bool()
    companion object : PacketMeta<EntityPosition>(0x27, ConnectionState.PLAY, Bound.CLIENT, ::EntityPosition)
}
