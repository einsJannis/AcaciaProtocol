package dev.einsjannis.acacia.protocol.packet.login.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SetCompression : Packet() {
    val threshold by varInt()
    companion object : PacketMeta<SetCompression>(0x03, ConnectionState.LOGIN, Bound.CLIENT, ::SetCompression)
}
