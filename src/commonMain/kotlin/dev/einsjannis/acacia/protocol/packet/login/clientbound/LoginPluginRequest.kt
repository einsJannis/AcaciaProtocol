package dev.einsjannis.acacia.protocol.packet.login.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class LoginPluginRequest : Packet() {
    var messageId by varInt()
    var channel by id()
    val data by byteArray({ it }, {})
    companion object : PacketMeta<LoginPluginRequest>(0x04, ConnectionState.LOGIN, Bound.CLIENT, ::LoginPluginRequest)
}
