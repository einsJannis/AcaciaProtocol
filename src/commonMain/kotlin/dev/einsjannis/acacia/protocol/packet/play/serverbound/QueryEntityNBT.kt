package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class QueryEntityNBT : Packet() {
    var transactionId by varInt()
    var entityId by varInt()

    companion object : PacketMeta<QueryEntityNBT>(0x0D, ConnectionState.PLAY, Bound.SERVER, ::QueryEntityNBT)
}
