package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.types.Facing
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

enum class DigStatus {
    STARTED_DIGGING,
    FINISHED_DIGGING,
    DROP_ITEM_STACK,
    DROP_ITEM,
    ITEM_STATUS_UPDATED,
    SWAP_ITEM_IN_HAND,
}

class PlayerDigging : Packet() {
    var status by varInt().mapped({ DigStatus.values()[it] }, { it.ordinal })
    var location by position()
    var face by byte().mapped(faceMapping)

    companion object : PacketMeta<PlayerDigging>(0x1B, ConnectionState.PLAY, Bound.SERVER, ::PlayerDigging) {
        val faceMapping = mapOf(
            0.toByte() to Facing.DOWN,
            1.toByte() to Facing.UP,
            2.toByte() to Facing.NORTH,
            3.toByte() to Facing.SOUTH,
            4.toByte() to Facing.WEST,
            5.toByte() to Facing.EAST,
        )
    }
}
