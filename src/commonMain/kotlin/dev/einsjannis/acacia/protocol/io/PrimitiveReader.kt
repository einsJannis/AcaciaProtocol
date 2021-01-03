package dev.einsjannis.acacia.protocol.io

import dev.einsjannis.acacia.protocol.primitives.Identifier
import dev.einsjannis.acacia.protocol.primitives.chat.ChatComponent
import dev.einsjannis.acacia.protocol.types.entity.EntityDataField
import dev.einsjannis.acacia.protocol.primitives.nbt.NbtTag
import dev.einsjannis.acacia.protocol.types.Position
import dev.einsjannis.acacia.protocol.primitives.UUID
import dev.einsjannis.acacia.protocol.primitives.nbt.SlotData

interface PrimitiveReader {
    fun readBoolean(): Boolean
    fun readByte(): Byte
    fun readUnsignedByte(): UByte
    fun readShort(): Short
    fun readUnsignedShort(): UShort
    fun readInt(): Int
    fun readLong(): Long
    fun readFloat(): Float
    fun readDouble(): Double
    fun readString(): String
    fun readChat(): ChatComponent
    fun readIdentifier(): Identifier
    fun readVarInt(): Int
    fun readVarLong(): Long
    fun readEntityMetadata(): List<EntityDataField>
    fun readSlot(): SlotData
    fun readNBTTag(): NbtTag
    fun readPosition(): Position
    fun readUUID(): UUID
    fun readByteArray(size: Int): ByteArray
    val remainingBytes: Int
}
