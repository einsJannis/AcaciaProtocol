package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class ChatMessage : Packet() {
    var message by string()

    companion object : PacketMeta<ChatMessage>(0x03, ConnectionState.PLAY, Bound.SERVER, ::ChatMessage)
}
