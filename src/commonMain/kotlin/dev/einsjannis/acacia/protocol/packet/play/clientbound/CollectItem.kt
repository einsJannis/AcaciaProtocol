package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class CollectItem : Packet() {
    var collectedEntityId by varInt()
    var collectorEntityId by varInt()
    var pickUpItemCount by varInt()
    companion object : PacketMeta<CollectItem>(0x55, ConnectionState.PLAY, Bound.CLIENT, ::CollectItem)
}
