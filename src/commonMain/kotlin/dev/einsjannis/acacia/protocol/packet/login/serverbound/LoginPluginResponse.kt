package dev.einsjannis.acacia.protocol.packet.login.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class LoginPluginResponse : Packet() {
    var messageId by id()
    var successful by bool()
    val data by byteArray({ it }, {})
    companion object : PacketMeta<LoginPluginResponse>(0x02, ConnectionState.LOGIN, Bound.SERVER, ::LoginPluginResponse)
}
