package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class ServerDifficulty : Packet() {
    var difficulty by ubyte()
    var locked by bool()
    companion object : PacketMeta<ServerDifficulty>(0x0D, ConnectionState.PLAY, Bound.CLIENT, ::ServerDifficulty)
}
