package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SpawnEntity : Packet() {
    var entityId by varInt()
    var objectUUID by uuid()
    var type by varInt()
    var x by double()
    var y by double()
    var z by double()
    var pitch by angle()
    var yaw by angle()
    var data by int()
    var velocityX by short()
    var velocityY by short()
    var velocityZ by short()
    companion object : PacketMeta<SpawnEntity>(0x00, ConnectionState.PLAY, Bound.CLIENT, ::SpawnEntity)
}
