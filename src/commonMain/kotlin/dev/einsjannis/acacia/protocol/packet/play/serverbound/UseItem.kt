package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.mappedAsHand

class UseItem : Packet() {
    var hand by varInt().mappedAsHand()

    companion object : PacketMeta<UseItem>(0x2F, ConnectionState.PLAY, Bound.SERVER, ::UseItem)
}
