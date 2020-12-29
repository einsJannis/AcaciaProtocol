package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class QueryBlockNBT : Packet() {
    var transactionId by varInt()
    var location by position()

    companion object : PacketMeta<QueryBlockNBT>(0x01, ConnectionState.PLAY, Bound.SERVER, ::QueryBlockNBT)
}
