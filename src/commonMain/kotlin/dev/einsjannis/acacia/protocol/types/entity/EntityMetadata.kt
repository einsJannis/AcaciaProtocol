package dev.einsjannis.acacia.protocol.types.entity

import dev.einsjannis.acacia.protocol.PacketObject
import dev.einsjannis.acacia.protocol.enumOrdinalMapping
import dev.einsjannis.acacia.protocol.packet.play.clientbound.Particle
import dev.einsjannis.acacia.protocol.types.Direction
import dev.einsjannis.acacia.protocol.types.Pose

enum class EntityMetadataType(val typeId: Int) {
    BYTE(0),
    VARINT(1),
    FLOAT(2),
    STRING(3),
    CHAT(4),
    OPTCHAT(5),
    SLOT(6),
    BOOLEAN(7),
    ROTATION(8),
    POSITION(9),
    OPTPOSITION(10),
    DIRECTION(11),
    OPTUUID(12),
    OPTBLOCKID(13),
    NBT(14),
    PARTICLE(15),
    VILLAGER_DATA(16),
    OPTVARINT(17),
    POSE(18),
}

sealed class EntityField(val type: EntityMetadataType) : PacketObject()
class ByteEntityField : EntityField(EntityMetadataType.BYTE) {
    var value by byte()
}

class VarIntEntityField : EntityField(EntityMetadataType.VARINT) {
    var value by varInt()
}

class FloatEntityField : EntityField(EntityMetadataType.FLOAT) {
    var value by float()
}

class StringEntityField : EntityField(EntityMetadataType.STRING) {
    var value by string()
}

class ChatEntityField : EntityField(EntityMetadataType.CHAT) {
    var value by chat()
}

class OptionalChatEntityField : EntityField(EntityMetadataType.OPTCHAT) {
    var isPresent by bool()
    var value by chat().onlyIf(::isPresent)
}

class SlotEntityField : EntityField(EntityMetadataType.SLOT) {
    var value by slot()
}

class BooleanEntityField : EntityField(EntityMetadataType.BOOLEAN) {
    var value by bool()
}

class RotationEntityField : EntityField(EntityMetadataType.ROTATION) {
    var x by float()
    var y by float()
    var z by float()
}

class PositionEntityField : EntityField(EntityMetadataType.POSITION) {
    val value by position()
}

class OptionalPositionEntityField : EntityField(EntityMetadataType.OPTPOSITION) {
    val isPresent by bool()
    val value by position().onlyIf(::isPresent)
}

class DirectionEntityField : EntityField(EntityMetadataType.DIRECTION) {
    val value by varInt().enumOrdinalMapping<Direction>()
}

class OptionalUUIDEntityField : EntityField(EntityMetadataType.OPTUUID) {
    var isPresent by bool()
    val value by uuid().onlyIf(::isPresent)
}

class OptionalBlockIdEntityField : EntityField(EntityMetadataType.OPTBLOCKID) {
    val value by varInt()
}

class NbtEntityField : EntityField(EntityMetadataType.NBT) {
    val value by nbtTag()
}

class ParticleEntityField : EntityField(EntityMetadataType.PARTICLE) {
    val value by `object`(::Particle)
}

class VillagerDataEntityField : EntityField(EntityMetadataType.VILLAGER_DATA) {
    val villagerType by varInt()
    val profession by varInt()
    val level by varInt()
}

class OptionalVarIntEntityField : EntityField(EntityMetadataType.OPTVARINT) {
    var value by varInt().mapped({ if (it == 0) null else it - 1 }, { if (it == null) 0 else it + 1 })
}

class PoseEntityField : EntityField(EntityMetadataType.POSE){
    var value by varInt().enumOrdinalMapping<Pose>()
}

data class EntityDataField(val index: Int, val data: EntityField)
