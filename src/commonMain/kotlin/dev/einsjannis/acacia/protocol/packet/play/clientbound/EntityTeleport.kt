package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityTeleport : Packet() {
    var entityId by varInt()
    var x by double()
    var y by double()
    var z by double()
    var yaw by angle()
    var pitch by angle()
    var onGround by bool()
    companion object : PacketMeta<EntityTeleport>(0x56, ConnectionState.PLAY, Bound.CLIENT, ::EntityTeleport)
}
