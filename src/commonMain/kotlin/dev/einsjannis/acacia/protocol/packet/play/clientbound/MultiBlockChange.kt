package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class MultiBlockChange : Packet() {
    var chunkSectionPosition by long()
    var boolean by bool()
    var blocksSize by varInt()
    var blocks by varLong().array(::blocksSize)
    companion object : PacketMeta<MultiBlockChange>(0x3B, ConnectionState.PLAY, Bound.CLIENT, ::MultiBlockChange)
}
