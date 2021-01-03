package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.types.Difficulty
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SetDifficulty : Packet() {
    var newDifficulty by byte().mapped({ Difficulty.values()[it.toInt()] }, { it.value })

    companion object : PacketMeta<SetDifficulty>(0x02, ConnectionState.PLAY, Bound.SERVER, ::SetDifficulty)
}
