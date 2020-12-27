package dev.einsjannis.acacia.protocol.packet.status.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Request : Packet() {
    companion object : PacketMeta<Request>(0x00, ConnectionState.STATUS, Bound.SERVER, ::Request)
}
