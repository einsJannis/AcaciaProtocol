package dev.einsjannis.acacia.protocol.packet.status.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

/**
 * Ping Packet
 *
 * (
 * Bound: Server;
 * State: Status;
 * Id: 0x01;
 * )
 *
 * Sent by the client to get the ping time for the server.
 */
class Ping : Packet() {
    
    /**
     * Ping payload
     */
    val payload by long()
    
    /**
     * PacketMeta for the Ping Packet
     */
    companion object : PacketMeta<Ping>(0x01, ConnectionState.STATUS, Bound.SERVER, ::Ping)
    
}
