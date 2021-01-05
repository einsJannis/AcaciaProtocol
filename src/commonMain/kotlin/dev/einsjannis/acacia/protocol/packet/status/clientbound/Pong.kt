package dev.einsjannis.acacia.protocol.packet.status.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

/**
 * Pong packet
 *
 * (
 * Bound: Client;
 * State: Status;
 * Id: 0x01;
 * )
 *
 * Sent by the server as a response to the [dev.einsjannis.acacia.protocol.packet.status.serverbound.Ping] Packet
 */
class Pong : Packet() {
    
    /**
     * The payload from the Ping Packet
     */
    val payload by long()
    
    /**
     * PacketMeta for the Pong Packet
     */
    companion object : PacketMeta<Pong>(0x01, ConnectionState.STATUS, Bound.CLIENT, ::Pong)
    
}
