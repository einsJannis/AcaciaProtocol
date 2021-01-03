package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.types.mappedAsHand

class Animation : Packet() {
    var hand by varInt().mappedAsHand()

    companion object : PacketMeta<Animation>(0x2C, ConnectionState.PLAY, Bound.SERVER, ::Animation)
}
