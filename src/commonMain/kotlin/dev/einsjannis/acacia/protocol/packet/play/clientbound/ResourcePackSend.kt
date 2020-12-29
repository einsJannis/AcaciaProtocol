package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class ResourcePackSend : Packet() {
    var url by string()
    var hash by string()
    companion object : PacketMeta<ResourcePackSend>(0x38, ConnectionState.PLAY, Bound.CLIENT, ::ResourcePackSend)
}
