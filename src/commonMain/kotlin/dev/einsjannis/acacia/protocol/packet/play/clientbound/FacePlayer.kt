package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class FacePlayer : Packet() {
    var location by varInt()
    var targetX by double()
    var targetY by double()
    var targetZ by double()
    var isEntity by bool()
    var entityId by varInt().onlyIf(::isEntity)
    var entityLocation by varInt().onlyIf(::isEntity)
    companion object : PacketMeta<FacePlayer>(0x33, ConnectionState.PLAY, Bound.CLIENT, ::FacePlayer)
}
