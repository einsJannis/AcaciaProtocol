package dev.einsjannis.acacia.protocol.packet.login.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class LoginStart : Packet() {
    val name by string()
    companion object : PacketMeta<LoginStart>(0x00, ConnectionState.LOGIN, Bound.SERVER, ::LoginStart)
}
