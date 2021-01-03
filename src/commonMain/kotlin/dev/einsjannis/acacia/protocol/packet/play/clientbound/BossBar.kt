package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.BossBarAction

class BossBar : Packet() {
    var uniqueId by uuid()
    var action by varInt().mapped({ BossBarAction.values()[it] }, { it.ordinal })
    var title by chat().onlyIf(
        { action == BossBarAction.ADD || action == BossBarAction.UPDATE_TITLE },
        { if (it) action = BossBarAction.UPDATE_TITLE }
    )
    var health by float().onlyIf(
        { action == BossBarAction.ADD || action == BossBarAction.UPDATE_HEALTH },
        { if (it) action = BossBarAction.UPDATE_HEALTH }
    )
    var color by varInt().onlyIf(
        { action == BossBarAction.ADD || action == BossBarAction.UPDATE_STYLE },
        { if (it) action = BossBarAction.UPDATE_STYLE }
    )
    var dividers by varInt().onlyIf(
        { action == BossBarAction.ADD || action == BossBarAction.UPDATE_STYLE },
        { if (it) action = BossBarAction.UPDATE_STYLE }
    )
    var flags by ubyte().onlyIf(
        { action == BossBarAction.ADD || action == BossBarAction.UPDATE_FLAGS },
        { if (it) action = BossBarAction.UPDATE_FLAGS }
    )
    companion object : PacketMeta<BossBar>(0x0C, ConnectionState.PLAY, Bound.CLIENT, ::BossBar)
}
