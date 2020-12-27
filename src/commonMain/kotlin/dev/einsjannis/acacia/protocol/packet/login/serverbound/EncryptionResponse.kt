package dev.einsjannis.acacia.protocol.packet.login.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class EncryptionResponse : Packet() {
    var sharedSecretLength by varInt()
    var sharedSecret by byteArray(::sharedSecretLength)
    var verifyTokenLength by varInt()
    var verifyToken by byteArray(::verifyTokenLength)
    companion object : PacketMeta<EncryptionResponse>(0x01, ConnectionState.LOGIN, Bound.SERVER, ::EncryptionResponse)
}
