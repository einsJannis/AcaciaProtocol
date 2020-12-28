package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class BlockBreakAnimation : Packet() {
    var entityId by varInt()
    var location by position()
    var destroyStage by byte()
    companion object : PacketMeta<BlockBreakAnimation>(0x08, ConnectionState.PLAY, Bound.CLIENT, ::BlockBreakAnimation)
}
