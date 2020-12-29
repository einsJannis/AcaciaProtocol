package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class CraftRecipeRequest : Packet() {
    var windowId by byte()
    var recipeId by id()
    var makeAll by bool()

    companion object : PacketMeta<CraftRecipeRequest>(0x19, ConnectionState.PLAY, Bound.SERVER, ::CraftRecipeRequest)
}
