package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class GenerateStructure : Packet() {
    var location by position()
    var levels by varInt()
    var keepJigsaws by bool()

    companion object : PacketMeta<GenerateStructure>(0x0F, ConnectionState.PLAY, Bound.SERVER, ::GenerateStructure)
}
