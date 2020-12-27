package dev.einsjannis.acacia.protocol.packet.login.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EncryptionRequest : Packet() {
    var serverId by string()
    var publicKeyLength by varInt()
    var publicKey by byteArray(::publicKeyLength)
    var verifyTokenLength by varInt()
    var verifyToken by byteArray(::verifyTokenLength)
    companion object : PacketMeta<EncryptionRequest>(0x01, ConnectionState.LOGIN, Bound.CLIENT, ::EncryptionRequest)
}
