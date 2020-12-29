package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.mappedAsHand

enum class InteractionType {
    INTERACT, ATTACK, INTERACT_AT
}

class InteractEntity : Packet() {
    var entityId by varInt()
    var type by varInt().mapped({ InteractionType.values()[it] }, { it.ordinal })
    var targetX by float().onlyIf({ type == InteractionType.INTERACT_AT }, {})
    var targetY by float().onlyIf({ type == InteractionType.INTERACT_AT }, {})
    var targetZ by float().onlyIf({ type == InteractionType.INTERACT_AT }, {})
    var hand by varInt().mappedAsHand()
        .onlyIf({ type == InteractionType.INTERACT_AT || type == InteractionType.INTERACT }, {})
    var sneaking by bool()

    companion object : PacketMeta<InteractEntity>(0x0E, ConnectionState.PLAY, Bound.SERVER, ::InteractEntity)
}
