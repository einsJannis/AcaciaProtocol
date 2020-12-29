package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EntityEffect : Packet() {
    var entityId by varInt()
    var effectId by byte()
    var aplifier by byte()
    var duration by varInt()
    var flags by varInt()
    companion object : PacketMeta<EntityEffect>(0x59, ConnectionState.PLAY, Bound.CLIENT, ::EntityEffect)
}
