package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.enumOrdinalMapping
import dev.einsjannis.acacia.protocol.types.Difficulty

class ServerDifficulty : Packet() {
    var difficulty by ubyte().mapped({it.toInt()},{it.toUByte()}).enumOrdinalMapping<Difficulty>()
    var locked by bool()
    companion object : PacketMeta<ServerDifficulty>(0x0D, ConnectionState.PLAY, Bound.CLIENT, ::ServerDifficulty)
}
