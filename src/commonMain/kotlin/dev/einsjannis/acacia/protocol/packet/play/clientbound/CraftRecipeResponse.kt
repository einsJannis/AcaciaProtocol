package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class CraftRecipeResponse : Packet() {
    var windowId by ubyte()
    var recipe by id()
    companion object : PacketMeta<CraftRecipeResponse>(0x2F, ConnectionState.PLAY, Bound.CLIENT, ::CraftRecipeResponse)
}
