package dev.einsjannis.acacia.protocol.packet.login.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Disconnect : Packet() {
    val reason by chat()
    companion object : PacketMeta<Disconnect>(0x00, ConnectionState.LOGIN, Bound.CLIENT, ::Disconnect)
}
