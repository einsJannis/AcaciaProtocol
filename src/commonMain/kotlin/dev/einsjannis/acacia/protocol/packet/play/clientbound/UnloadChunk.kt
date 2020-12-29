package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UnloadChunk : Packet() {
    val x by int()
    val z by int()
    companion object : PacketMeta<UnloadChunk>(0x1C, ConnectionState.PLAY, Bound.CLIENT, ::UnloadChunk)
}
