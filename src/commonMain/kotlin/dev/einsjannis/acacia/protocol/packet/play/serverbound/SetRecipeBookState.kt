package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.enumOrdinalMapping

enum class RecipeBookState {
    CRAFTING,
    FURNACE,
    BLAST_FURNACE,
    SMOKER,
}

class SetRecipeBookState : Packet() {
    var bookId by varInt().enumOrdinalMapping<RecipeBookState>()
    var bookOpen by bool()
    var filterActive by bool()

    companion object : PacketMeta<SetRecipeBookState>(0x1F, ConnectionState.PLAY, Bound.SERVER, ::SetRecipeBookState)
}
