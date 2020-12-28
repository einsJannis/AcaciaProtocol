package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class ChatMessage : Packet() {
    var jsonData by chat()
    var messagePosition by byte()
    var sender by uuid()
    companion object : PacketMeta<ChatMessage>(0x0E, ConnectionState.PLAY, Bound.CLIENT, ::ChatMessage)
}
