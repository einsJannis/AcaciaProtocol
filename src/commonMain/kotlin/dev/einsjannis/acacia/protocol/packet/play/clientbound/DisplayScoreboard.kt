package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class DisplayScoreboard : Packet() {
    var location by byte()
    var scorename by string()
    companion object : PacketMeta<DisplayScoreboard>(0x43, ConnectionState.PLAY, Bound.CLIENT, ::DisplayScoreboard)
}
