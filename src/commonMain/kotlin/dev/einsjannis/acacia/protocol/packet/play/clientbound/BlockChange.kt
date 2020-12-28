package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class BlockChange : Packet() {
    var location by position()
    var blockId by varInt()
    companion object : PacketMeta<BlockChange>(0x0B, ConnectionState.PLAY, Bound.CLIENT, ::BlockChange)
}
