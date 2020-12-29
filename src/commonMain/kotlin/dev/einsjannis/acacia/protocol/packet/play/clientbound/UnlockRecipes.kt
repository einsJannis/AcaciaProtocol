package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UnlockRecipes : Packet() {
    var action by varInt()
    var craftingRecipeBookOpen by bool()
    var craftingRecipeBookFilterActive by bool()
    var smeltingRecipeBookOpen by bool()
    var smeltingRecipeBookFilterAction by bool()
    var blastRecipeBookOpen by bool()
    var blastRecipeBookFilterAction by bool()
    var smokerRecipeBookOpen by bool()
    var smokerRecipeBookFilterAction by bool()
    var arrayASize by varInt()
    var arrayA by id().array(::arrayASize)
    var arrayBSize by varInt().onlyIf({ action == 0 }, { if (it) action = 0 })
    var arrayB by id().array({ arrayBSize!! }, { arrayBSize = it }).onlyIf({ action == 0 }, { if (it) action = 0 })
    companion object : PacketMeta<UnlockRecipes>(0x35, ConnectionState.PLAY, Bound.CLIENT, ::UnlockRecipes)
}
