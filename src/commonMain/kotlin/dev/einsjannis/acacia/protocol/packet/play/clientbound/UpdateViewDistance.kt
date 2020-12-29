package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UpdateViewDistance : Packet() {
    var viewDistance by varInt()
    companion object : PacketMeta<UpdateViewPosition>(0x41, ConnectionState.PLAY, Bound.CLIENT, ::UpdateViewPosition)
}
