package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Title : Packet() {
    var action by varInt()
    var title by chat().onlyIf({ action == 0 }, { if (it) action = 0 })
    var subtitle by chat().onlyIf({ action == 1 }, { if (it) action = 1 })
    var actionBar by chat().onlyIf({ action == 2 }, { if (it) action = 2 })
    var fadeIn by int().onlyIf({ action == 3 }, { if (it) action = 3 })
    var stay by int().onlyIf({ action == 3 }, { if (it) action = 3 })
    var fadeOut by int().onlyIf({ action == 3 }, { if (it) action = 3 })
    companion object : PacketMeta<Title>(0x4F, ConnectionState.PLAY, Bound.CLIENT, ::Title)
}
