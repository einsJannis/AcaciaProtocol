package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.mappedAsHand

class PlayerBlockPlacement : Packet() {
    var hand by varInt().mappedAsHand()
    var location by position()
    var face by varInt().mapped({ it.toByte() }, { it.toInt() }).mapped(PlayerDigging.faceMapping)
    var cursorPositionX by float()
    var cursorPositionY by float()
    var cursorPositionZ by float()
    var insideBlock by bool()

    companion object :
        PacketMeta<PlayerBlockPlacement>(0x2E, ConnectionState.PLAY, Bound.SERVER, ::PlayerBlockPlacement)
}
