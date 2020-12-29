package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UpdateHealth : Packet() {
    var health by float()
    var food by varInt()
    var foodSaturation by float()
    companion object : PacketMeta<UpdateHealth>(0x49, ConnectionState.PLAY, Bound.CLIENT, ::UpdateHealth)
}
