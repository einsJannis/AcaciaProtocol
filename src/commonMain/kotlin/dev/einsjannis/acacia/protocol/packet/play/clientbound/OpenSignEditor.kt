package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class OpenSignEditor : Packet() {
    var location by position()
    companion object : PacketMeta<OpenSignEditor>(0x2E, ConnectionState.PLAY, Bound.CLIENT, ::OpenSignEditor)
}
