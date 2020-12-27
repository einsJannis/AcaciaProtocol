package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SpawnLivingEntity : Packet() {
    var entityId by varInt()
    var entityUUID by uuid()
    var type by varInt()
    var x by double()
    var y by double()
    var z by double()
    var yaw by angle()
    var pitch by angle()
    var headPitch by angle()
    var velocityX by short()
    var velocityY by short()
    var velocityZ by short()
    companion object : PacketMeta<SpawnLivingEntity>(0x02, ConnectionState.PLAY, Bound.CLIENT, ::SpawnLivingEntity)
}
