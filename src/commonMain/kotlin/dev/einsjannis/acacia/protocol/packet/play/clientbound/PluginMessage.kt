package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class PluginMessage : Packet() {
    var channel by id()
    var data by byteArray({ it }, {  })
    companion object : PacketMeta<PluginMessage>(0x17, ConnectionState.PLAY, Bound.CLIENT, ::PluginMessage)
}
