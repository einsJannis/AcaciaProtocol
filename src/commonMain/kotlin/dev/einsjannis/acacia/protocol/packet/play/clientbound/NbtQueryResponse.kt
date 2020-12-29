package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class NbtQueryResponse : Packet() {
    var transactionId by varInt()
    var nbt by nbtTag()
    companion object : PacketMeta<NbtQueryResponse>(0x54, ConnectionState.PLAY, Bound.CLIENT, ::NbtQueryResponse)
}
