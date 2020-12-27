package dev.einsjannis.acacia.protocol.entity

import dev.einsjannis.acacia.protocol.Direction
import dev.einsjannis.acacia.protocol.Pose
import dev.einsjannis.acacia.protocol.Position
import dev.einsjannis.acacia.protocol.UUID
import dev.einsjannis.acacia.protocol.chat.ChatComponent
import dev.einsjannis.acacia.protocol.nbt.NbtTag
import dev.einsjannis.acacia.protocol.nbt.SlotData

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

sealed class EntityField(val type: EntityMetadataType)
data class ByteEntityField(val value: Byte) : EntityField(EntityMetadataType.BYTE)
data class VarIntEntityField(val value: Int) : EntityField(EntityMetadataType.VARINT)
data class FloatEntityField(val value: Float) : EntityField(EntityMetadataType.FLOAT)
data class StringEntityField(val value: String) : EntityField(EntityMetadataType.STRING)
data class ChatEntityField(val value: ChatComponent) : EntityField(EntityMetadataType.CHAT)
data class OptionalChatEntityField(val value: ChatComponent?) : EntityField(EntityMetadataType.OPTCHAT)
data class SlotEntityField(val value: SlotData) : EntityField(EntityMetadataType.SLOT)
data class BooleanEntityField(val value: Boolean) : EntityField(EntityMetadataType.BOOLEAN)
data class RotationEntityField(val x: Float, val y: Float, val z: Float) :
    EntityField(EntityMetadataType.ROTATION)

data class PositionEntityField(val position: Position) : EntityField(EntityMetadataType.POSITION)
data class OptionalPositionEntityField(val position: Position?) : EntityField(EntityMetadataType.OPTPOSITION)
data class DirectionEntityField(val direction: Direction) : EntityField(EntityMetadataType.DIRECTION)
data class OptionalUUIDEntityField(val uuid: UUID?) : EntityField(EntityMetadataType.OPTUUID)
data class OptionalBlockIdEntityField(val blockId: Int) : EntityField(EntityMetadataType.OPTBLOCKID)
data class NbtEntityField(val nbt: NbtTag) : EntityField(EntityMetadataType.NBT)
data class ParticleEntityField(val particle: Int) :
    EntityField(EntityMetadataType.PARTICLE) // TODO particle data

data class VillagerDataEntityField(val villagerType: Int, val profession: Int, val level: Int) :
    EntityField(EntityMetadataType.VILLAGER_DATA)

data class OptionalVarIntEntityField(val value: Int?) : EntityField(EntityMetadataType.OPTVARINT)
data class PoseEntityField(val pose: Pose) : EntityField(EntityMetadataType.POSE)

data class EntityDataField(val index: Int, val data: EntityField)
