package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class DestroyEntities : Packet() {
    var count by varInt()
    var entitiesIds by varInt().array(::count)
    companion object : PacketMeta<DestroyEntities>(0x36, ConnectionState.PLAY, Bound.CLIENT, ::DestroyEntities)
}
