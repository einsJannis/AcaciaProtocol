package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class CreativeInventoryAction : Packet() {
    var slotId by short()
    var clickedItem by slot()

    companion object :
        PacketMeta<CreativeInventoryAction>(0x28, ConnectionState.PLAY, Bound.SERVER, ::CreativeInventoryAction)
}
