package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*

class DeclareCommands : Packet() {
    var count by varInt()
    var nodes by `object`(::CommandNode).array(::count)
    var rootIndex by varInt()
    companion object : PacketMeta<DeclareCommands>(0x10, ConnectionState.PLAY, Bound.CLIENT, ::DeclareCommands)
}
