package dev.einsjannis.acacia.protocol.packet.handshaking.serverbound

import dev.einsjannis.acacia.protocol.*

/**
 * Handshake Packet
 * Bound: Server
 * State: Handshake
 * Id: 0x00
 * This causes the server to switch into the target state.
 */
class Handshake : Packet() {

    /**
     * Protocol version number
     */
    var protocolVersion by varInt()

    /**
     * Hostname or IP, e.g. localhost or 127.0.0.1, that was used to connect. Note that SRV records are a complete
     * redirect, e.g. if _minecraft._tcp.example.com points to mc.example.org, users connecting to example.com will
     * provide mc.example.org as server address in addition to connecting to it.
     */
    var serverAddress by string()

    /**
     * Port that was used to connect.
     */
    var serverPort by ushort()

    /**
     * Next ConnectionState to switch to.
     */
    var nextState by varInt().enumOrdinalMapping<ConnectionState>()

    /**
     * PacketMeta for the Handshake Packet
     */
    companion object : PacketMeta<Handshake>(0x00, ConnectionState.HANDSHAKE, Bound.SERVER, ::Handshake)

}
