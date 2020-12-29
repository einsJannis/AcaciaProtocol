package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SpawnPosition : Packet() {
    var location by position()
    companion object : PacketMeta<SpawnPainting>(0x42, ConnectionState.PLAY, Bound.CLIENT, ::SpawnPainting)
}
