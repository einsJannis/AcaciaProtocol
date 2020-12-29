package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class KeepAlive : Packet() {
    var keepAliveId by long()
    companion object : PacketMeta<KeepAlive>(0x1F, ConnectionState.PLAY, Bound.CLIENT, ::KeepAlive)
}
