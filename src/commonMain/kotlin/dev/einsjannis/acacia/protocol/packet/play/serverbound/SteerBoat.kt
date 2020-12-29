package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SteerBoat : Packet() {
    var leftPaddle by bool()
    var rightPaddle by bool()

    companion object : PacketMeta<SteerBoat>(0x17, ConnectionState.PLAY, Bound.SERVER, ::SteerBoat)
}
