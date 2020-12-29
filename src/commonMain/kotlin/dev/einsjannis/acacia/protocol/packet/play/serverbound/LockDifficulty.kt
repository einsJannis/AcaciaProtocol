package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class LockDifficulty : Packet() {
    var locked by bool()

    companion object : PacketMeta<LockDifficulty>(0x11, ConnectionState.PLAY, Bound.SERVER, ::LockDifficulty)
}
