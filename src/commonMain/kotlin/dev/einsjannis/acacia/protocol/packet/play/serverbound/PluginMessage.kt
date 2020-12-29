package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PluginMessage : Packet() {
    var channel by id()
    var data by byteArray({ it }, {})

    companion object : PacketMeta<PluginMessage>(0x0B, ConnectionState.PLAY, Bound.SERVER, ::PluginMessage)
}
