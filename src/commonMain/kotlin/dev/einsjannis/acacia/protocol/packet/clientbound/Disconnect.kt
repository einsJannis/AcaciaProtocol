package dev.einsjannis.acacia.protocol.packet.clientbound

import dev.einsjannis.acacia.protocol.Packet

class Disconnect : Packet() {
    val reason by chat()
}
