package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class RemoveEntityEffect : Packet() {
    var entityId by varInt()
    var effectId by byte()
    companion object : PacketMeta<RemoveEntityEffect>(0x37, ConnectionState.PLAY, Bound.CLIENT, ::RemoveEntityEffect)
}
