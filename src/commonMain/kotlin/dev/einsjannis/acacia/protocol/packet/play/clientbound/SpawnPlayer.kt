package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SpawnPlayer : Packet() {
    var entityId by varInt()
    var playerUUID by uuid()
    var x by double()
    var y by double()
    var z by double()
    var yaw by double()
    var pitch by double()
    companion object : PacketMeta<SpawnPlayer>(0x04, ConnectionState.PLAY, Bound.CLIENT, ::SpawnPlayer)
}
