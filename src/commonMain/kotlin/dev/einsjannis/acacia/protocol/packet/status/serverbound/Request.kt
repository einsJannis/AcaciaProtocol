package dev.einsjannis.acacia.protocol.packet.status.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

/**
 * RequestPacket
 *
 * (
 * Bound: Server;
 * State: Status;
 * Id: 0x00;
 * )
 *
 * Sent by the client after sending the [dev.einsjannis.acacia.protocol.packet.handshaking.serverbound.Handshake] Packet
 * if the field [dev.einsjannis.acacia.protocol.packet.handshaking.serverbound.Handshake.nextState] was
 * [dev.einsjannis.acacia.protocol.ConnectionState.STATUS]
 */
class Request : Packet() {
    
    /**
     * PacketMeta for the Request Packet
     */
    companion object : PacketMeta<Request>(0x00, ConnectionState.STATUS, Bound.SERVER, ::Request)
    
}
