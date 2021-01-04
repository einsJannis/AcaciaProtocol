package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Disconnect : Packet() {
    var reason by chat()
    companion object : PacketMeta<Disconnect>(0x19, ConnectionState.PLAY, Bound.CLIENT, ::Disconnect)
}
