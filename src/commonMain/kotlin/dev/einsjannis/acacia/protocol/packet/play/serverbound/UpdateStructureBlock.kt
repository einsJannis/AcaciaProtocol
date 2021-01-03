package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.primitives.FlagBit
import dev.einsjannis.acacia.protocol.primitives.Flags
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.bitFlag
import dev.einsjannis.acacia.protocol.enumOrdinalMapping

enum class StructureBlockAction {
    UPDATE_DATA,
    SAVE_THE_STRUCTURE,
    LOAD_THE_STRUCTURE,
    DETECT_SIZE,
}

enum class StructureBlockMode {
    SAVE, LOAD, CORNER, DATA,
}

enum class StructureBlockMirror {
    NONE, LEFT_RIGHT, FRONT_BACK,
}

enum class StructureBlockRotation {
    NONE, CLOCKWISE_90, CLOCKWISE_180, COUNTERCLOCKWISE_90,
}

class StructureBlockFlag(value: Int = 0) : Flags(value) {
    var ignoreEntities by FlagBit(0)
    var showAir by FlagBit(1)
    var showBoundingBox by FlagBit(2)
}

class UpdateStructureBlock : Packet() {
    var location by position()
    var action by varInt().enumOrdinalMapping<StructureBlockAction>()
    var mode by varInt().enumOrdinalMapping<StructureBlockMode>()
    var name by string()
    var offsetX by byte()
    var offsetY by byte()
    var offsetZ by byte()
    var sizeX by byte()
    var sizeY by byte()
    var sizeZ by byte()
    var mirror by varInt().enumOrdinalMapping<StructureBlockMirror>()
    var rotation by varInt().enumOrdinalMapping<StructureBlockRotation>()
    var metadata by string()
    var integrity by float()
    var seed by varLong()
    var flags by byte().mapped({ it.toInt() }, { it.toByte() }).bitFlag(::StructureBlockFlag)

    companion object :
        PacketMeta<UpdateStructureBlock>(0x2A, ConnectionState.PLAY, Bound.SERVER, ::UpdateStructureBlock)
}

