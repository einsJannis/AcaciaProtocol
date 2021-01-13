package dev.einsjannis.acacia.protocol.exception

import dev.einsjannis.acacia.protocol.PacketObject

class InvalidPacketObjectException(val packet: PacketObject) : Exception() {
    
    override val message: String =
        "The packet object $packet is invalid!"
    
}
