package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.enumOrdinalMapping

enum class AdvancementTabOption {
    OPENED_TAB,
    CLOSED_SCREEN,
}

class AdvancementTab : Packet() {
    var action by varInt().enumOrdinalMapping<AdvancementTabOption>()
    var tabId by id().onlyIf({ action == AdvancementTabOption.OPENED_TAB })

    companion object : PacketMeta<AdvancementTab>(0x22, ConnectionState.PLAY, Bound.SERVER, ::AdvancementTab)
}
