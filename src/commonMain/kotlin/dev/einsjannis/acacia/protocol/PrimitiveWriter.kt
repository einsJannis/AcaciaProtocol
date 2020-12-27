package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.chat.ChatComponent
import dev.einsjannis.acacia.protocol.entity.EntityDataField
import dev.einsjannis.acacia.protocol.nbt.NbtTag
import dev.einsjannis.acacia.protocol.nbt.SlotData

interface PrimitiveWriter {
    fun writeBoolean(value: Boolean)
    fun writeByte(value: Byte)
    fun writeUnsignedByte(value: UByte)
    fun writeShort(value: Short)
    fun writeUnsignedShort(value: UShort)
    fun writeInt(value: Int)
    fun writeLong(value: Long)
    fun writeFloat(value: Float)
    fun writeDouble(value: Double)
    fun writeString(value: String)
    fun writeChat(value: ChatComponent)
    fun writeIdentifier(value: Identifier)
    fun writeVarInt(value: Int)
    fun writeVarLong(value: Long)
    fun writeEntityMetadata(value: List<EntityDataField>)
    fun writeSlot(value: SlotData)
    fun writeNBTTag(value: NbtTag)
    fun writePosition(value: Position)
    fun writeUUID(value: UUID)
    fun writeByteArray(value: ByteArray)
}
