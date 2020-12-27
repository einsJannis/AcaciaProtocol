package dev.einsjannis.acacia.protocol.packet.handshaking.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Handshake : Packet() {
    var protocolVersion by varInt()
    var serverAddress by string()
    var serverPort by ushort()
    var nextState by varInt().mapped({ ConnectionState.values()[it] }, { it.ordinal })
    companion object : PacketMeta<Handshake>(0x00, ConnectionState.HANDSHAKE, Bound.SERVER, ::Handshake)
}
