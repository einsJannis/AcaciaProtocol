package dev.einsjannis.acacia.protocol.packet.status.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

/**
 * Response Packet
 *
 * (
 * Bound: Client;
 * State: Status;
 * Id: 0x00;
 * )
 *
 * Sent by the server as a response for the [dev.einsjannis.acacia.protocol.packet.status.serverbound.Request] Packet
 */
class Response : Packet() {
    
    /**
     * Server list information for the server
     */
    val jsonResponse by string()
    
    /**
     * PacketMeta for the Response Packet
     */
    companion object : PacketMeta<Response>(0x00, ConnectionState.HANDSHAKE, Bound.CLIENT, ::Response)
    
}
