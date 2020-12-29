package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class ChangeGameState : Packet() {
    var reason by ubyte()
    var value by float()
    companion object : PacketMeta<ChangeGameState>(0x1D, ConnectionState.PLAY, Bound.CLIENT, ::ChangeGameState)
}
