package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*

class BlockEntityData : Packet() {
    var location by position()
    var action by ubyte()
    var nbtData by nbtTag()
    companion object : PacketMeta<BlockEntityData>(0x09, ConnectionState.PLAY, Bound.CLIENT, ::BlockEntityData)
}
