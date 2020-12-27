package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.chat.ChatComponent
import dev.einsjannis.acacia.protocol.entity.EntityDataField
import dev.einsjannis.acacia.protocol.nbt.NbtTag
import dev.einsjannis.acacia.protocol.nbt.SlotData

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
