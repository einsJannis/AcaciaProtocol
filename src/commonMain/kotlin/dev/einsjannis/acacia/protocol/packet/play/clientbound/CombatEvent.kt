package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class CombatEvent : Packet() {
    var event by varInt()
    var duration by varInt().onlyIf({ event == 1 }, { if (it) event = 1 })
    var playerId by varInt().onlyIf({ event == 2 }, { if (it) event = 2 })
    var entityId by int().onlyIf({ event != 0 }, {  })
    var message by chat().onlyIf({ event == 2 }, { if (it) event = 2 })
    companion object : PacketMeta<CombatEvent>(0x31, ConnectionState.PLAY, Bound.CLIENT, ::CombatEvent)
}
