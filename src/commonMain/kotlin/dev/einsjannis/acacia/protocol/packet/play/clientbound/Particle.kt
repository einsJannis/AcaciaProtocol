package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Particle : Packet() {
    var particleId by int()
    var longDistance by bool()
    var x by double()
    var y by double()
    var z by double()
    var offsetX by float()
    var offsetY by float()
    var offsetZ by float()
    var particleData by float()
    var particleCount by int()
    var blockState by varInt().onlyIf({ particleId == 3 || particleId == 23 }, { })
    var red by float().onlyIf({ particleId == 14 }, { particleId = 14 })
    var green by float().onlyIf({ particleId == 14 }, { particleId = 14 })
    var blue by float().onlyIf({ particleId == 14 }, { particleId = 14 })
    var scale by float().onlyIf({ particleId == 14 }, { particleId = 14 })
    var item by slot().onlyIf({ particleId == 32 }, { particleId = 32 })
    companion object : PacketMeta<Particle>(0x22, ConnectionState.PLAY, Bound.CLIENT, ::Particle)
}
