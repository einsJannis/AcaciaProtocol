package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.Facing

class SpawnPainting : Packet() {
    var entityId by varInt()
    var entityUUID by uuid()
    var motive by varInt()
    var location by position()
    var direction by byte().mapped(
        { directionMap[it]!! },
        { t -> directionMap.entries.find { it.value == t }!!.key }
    )
    companion object : PacketMeta<SpawnPainting>(0x03, ConnectionState.PLAY, Bound.CLIENT, ::SpawnPainting) {
        val directionMap = mapOf(
            0.toByte() to Facing.SOUTH,
            1.toByte() to Facing.WEST,
            2.toByte() to Facing.NORTH,
            3.toByte() to Facing.EAST
        )
    }
}
