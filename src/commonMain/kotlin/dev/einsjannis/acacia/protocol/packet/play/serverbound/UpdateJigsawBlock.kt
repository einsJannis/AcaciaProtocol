package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UpdateJigsawBlock : Packet() {
    var location by position()
    var name by id()
    var target by id()
    var pool by id()
    var finalState by string()
    var jointType by string()

    companion object : PacketMeta<UpdateJigsawBlock>(0x29, ConnectionState.PLAY, Bound.SERVER, ::UpdateJigsawBlock)
}
