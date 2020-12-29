package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class AttachEntity : Packet() {
    var attachedEntityId by int()
    var holdingEntityId by int()
    companion object : PacketMeta<AttachEntity>(0x45, ConnectionState.PLAY, Bound.CLIENT, ::AttachEntity)
}
