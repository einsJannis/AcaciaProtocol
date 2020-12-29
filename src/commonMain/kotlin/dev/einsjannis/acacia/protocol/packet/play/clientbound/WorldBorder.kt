package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class WorldBorder : Packet() {
    var action by varInt()
    var diameter by double().onlyIf({ action == 0 }, { if (it) action = 0 })
    var x by double().onlyIf({ action == 2 || action == 3 }, { if (it) action = 2 })
    var z by double().onlyIf({ action == 2 || action == 3 }, { if (it) action = 2 })
    var oldDiameter by double().onlyIf({ action == 1 || action == 3 }, { if (it) action = 1 })
    var newDiameter by double().onlyIf({ action == 1 || action == 3 }, { if (it) action = 1 })
    var speed by varLong().onlyIf({ action == 1 || action == 3 }, { if (it) action = 1 })
    var portalTeleportBoundary by varInt().onlyIf({ action == 3 }, { if (it) action = 3 })
    var warningTime by varInt().onlyIf({ action == 3 || action == 4 }, { if (it) action = 4 })
    var warningBlocks by varInt().onlyIf({ action == 3 || action == 5 }, { if (it) action = 5 })
    companion object : PacketMeta<WorldBorder>(0x3D, ConnectionState.PLAY, Bound.CLIENT, ::WorldBorder)
}
