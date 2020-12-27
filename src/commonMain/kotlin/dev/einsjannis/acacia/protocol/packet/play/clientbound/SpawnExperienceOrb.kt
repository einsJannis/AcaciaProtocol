package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SpawnExperienceOrb : Packet() {
    var entityId by varInt()
    var x by double()
    var y by double()
    var z by double()
    var count by short()
    companion object : PacketMeta<SpawnExperienceOrb>(0x01, ConnectionState.PLAY, Bound.CLIENT, ::SpawnExperienceOrb)
}
