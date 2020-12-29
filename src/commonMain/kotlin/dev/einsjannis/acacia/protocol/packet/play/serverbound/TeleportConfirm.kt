package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class TeleportConfirm : Packet() {
    var teleportId by varInt()

    companion object : PacketMeta<TeleportConfirm>(0x00, ConnectionState.PLAY, Bound.SERVER, ::TeleportConfirm)
}
