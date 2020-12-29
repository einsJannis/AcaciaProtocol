package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PickItem : Packet() {
    var slotToUse by varInt()

    companion object : PacketMeta<PickItem>(0x18, ConnectionState.PLAY, Bound.SERVER, ::PickItem)
}
