package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class ScoreboardObjective : Packet() {
    var objectiveName by string()
    var mode by byte()
    var objectiveValue by chat().onlyIf({ mode == 0.toByte() || mode == 2.toByte() }, {  })
    var type by varInt().onlyIf({ mode == 0.toByte() || mode == 2.toByte() }, {  })
    companion object : PacketMeta<ScoreboardObjective>(0x4A, ConnectionState.PLAY, Bound.CLIENT, ::ScoreboardObjective)
}
