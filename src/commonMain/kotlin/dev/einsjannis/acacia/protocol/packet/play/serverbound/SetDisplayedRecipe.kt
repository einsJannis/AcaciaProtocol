package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SetDisplayedRecipe : Packet() {
    var recipeId by id()

    companion object : PacketMeta<SetDisplayedRecipe>(0x1E, ConnectionState.PLAY, Bound.SERVER, ::SetDisplayedRecipe)
}
