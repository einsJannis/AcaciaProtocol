package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SetExperience : Packet() {
    var experienceBar by float()
    var level by varInt()
    var totalExperience by varInt()
    companion object : PacketMeta<SetExperience>(0x48, ConnectionState.PLAY, Bound.CLIENT, ::SetExperience)
}
