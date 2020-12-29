package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UpdateViewPosition : Packet() {
    var x by varInt()
    var z by varInt()
    companion object : PacketMeta<UpdateViewPosition>(0x40, ConnectionState.PLAY, Bound.CLIENT, ::UpdateViewPosition)
}
