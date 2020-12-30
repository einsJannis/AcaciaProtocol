package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UpdateSign : Packet() {
    var location by position()
    var line1 by string()
    var line2 by string()
    var line3 by string()
    var line4 by string()

    companion object : PacketMeta<UpdateSign>(0x2B, ConnectionState.PLAY, Bound.SERVER, ::UpdateSign)
}
