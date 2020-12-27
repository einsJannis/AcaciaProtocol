package dev.einsjannis.acacia.protocol.packet.login.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class LoginSuccess : Packet() {
    var uniqueId by uuid()
    var username by string()
    companion object : PacketMeta<LoginSuccess>(0x02, ConnectionState.LOGIN, Bound.CLIENT, ::LoginSuccess)
}
