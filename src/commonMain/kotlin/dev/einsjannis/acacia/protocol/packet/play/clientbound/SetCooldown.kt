package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SetCooldown : Packet() {
    var itemId by varInt()
    var cooldownTicks by varInt()
    companion object : PacketMeta<SetCooldown>(0x16, ConnectionState.PLAY, Bound.CLIENT, ::SetCooldown)
}
