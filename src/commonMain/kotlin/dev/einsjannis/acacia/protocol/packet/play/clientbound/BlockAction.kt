package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class BlockAction : Packet() {
    var location by position()
    var actionId by ubyte()
    var actionParam by ubyte()
    var blockType by varInt()
    companion object : PacketMeta<BlockAction>(0x0A, ConnectionState.PLAY, Bound.CLIENT, ::BlockAction)
}
