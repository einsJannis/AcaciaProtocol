package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class KeepAlive : Packet() {
    var keepAliveId by long()

    companion object : PacketMeta<KeepAlive>(0x10, ConnectionState.PLAY, Bound.SERVER, ::KeepAlive)
}
