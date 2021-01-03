package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.Recipe

class DeclareRecipes : Packet() {
    var count by varInt()
    var recipes by `object`(::Recipe).array(::count)
    companion object : PacketMeta<DeclareCommands>(0x5A, ConnectionState.PLAY, Bound.CLIENT, ::DeclareCommands)
}
