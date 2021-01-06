package dev.einsjannis.acacia.protocol.io

import dev.einsjannis.acacia.protocol.primitives.Identifier
import dev.einsjannis.acacia.protocol.primitives.UUID
import dev.einsjannis.acacia.protocol.primitives.chat.ChatComponent
import dev.einsjannis.acacia.protocol.primitives.chat.ChatSerializer
import dev.einsjannis.acacia.protocol.primitives.nbt.*
import dev.einsjannis.acacia.protocol.types.Position
import dev.einsjannis.acacia.protocol.types.entity.EntityDataField
import kotlin.experimental.or

/**
 * Abstract implementation for a [PrimitiveWriter]
 */
abstract class AbstractWriter : PrimitiveWriter {
    
    /**
     * Writes a Boolean as a Byte.
     */
    override fun writeBoolean(value: Boolean) = writeByte(if (value) 0x01 else 0x00)
    
    /**
     * Writes a Byte as a ByteArray.
     */
    override fun writeByte(value: Byte) = writeByteArray(byteArrayOf(value))
    
    /**
     * Writes a UByte as a Byte.
     */
    override fun writeUnsignedByte(value: UByte) = writeByte(value.toByte())
    
    /**
     * Writes a Short as a ByteArray.
     */
    override fun writeShort(value: Short) = writeByteArray(
        byteArrayOf(
            (value.toInt() ushr 8).toByte(),
            value.toByte()
        )
    )
    
    /**
     * Writes a UShort as a Short.
     */
    override fun writeUnsignedShort(value: UShort) = writeShort(value.toShort())
    
    /**
     * Writes a Int as a ByteArray.
     */
    override fun writeInt(value: Int) = writeByteArray(
        byteArrayOf(
            (value ushr 24).toByte(),
            (value ushr 16).toByte(),
            (value ushr 8).toByte(),
            value.toByte()
        )
    )
    
    /**
     * Writes a Long as a ByteArray.
     */
    override fun writeLong(value: Long) = writeByteArray(
        byteArrayOf(
            (value ushr 56).toByte(),
            (value ushr 48).toByte(),
            (value ushr 40).toByte(),
            (value ushr 32).toByte(),
            (value ushr 24).toByte(),
            (value ushr 16).toByte(),
            (value ushr 8).toByte(),
            value.toByte()
        )
    )
    
    /**
     * Writes a Float as a Int.
     */
    override fun writeFloat(value: Float) = writeInt(value.toRawBits())
    
    /**
     * Writes a Double as a Long.
     */
    override fun writeDouble(value: Double) = writeLong(value.toRawBits())
    
    /**
     * Writes a String as a ByteArray with the size prefixed as a VarInt.
     */
    override fun writeString(value: String) {
        writeVarInt(value.length)
        writeByteArray(value.encodeToByteArray())
    }
    
    /**
     * Writes a ChatComponent as a String.
     */
    override fun writeChat(value: ChatComponent) = writeString(ChatSerializer.toString(value))
    
    /**
     * Writes a Identifier as a String.
     */
    override fun writeIdentifier(value: Identifier) = writeString(value.toString())
    
    /**
     * Writes a Int as a VarInt.
     */
    override fun writeVarInt(value: Int) {
        var work = value
        do {
            var temp = (work and 0b01111111).toByte()
            work = work ushr 7
            if (work != 0) {
                temp = temp or 0b10000000.toByte()
            }
            writeByte(temp)
        } while (work != 0)
    }
    
    /**
     * Writes a Long as a VarLong.
     */
    override fun writeVarLong(value: Long) {
        var work = value
        do {
            var temp = (work and 0b01111111).toByte()
            work = work ushr 7
            if (work != 0L) temp = temp or 0b10000000.toByte()
            writeByte(temp)
        } while (work != 0L)
    }
    
    /**
     * Writes EntityMeta as a ByteArray.
     */
    override fun writeEntityMetadata(value: List<EntityDataField>) {
        value.forEach {
            writeUnsignedByte(it.index.toUByte())
            writeVarInt(it.data.type.typeId)
            it.data.delegates.forEach { it.writeValue(this) }
        }
        writeUnsignedByte(0xFF.toUByte())
    }
    
    /**
     * Writes a Slot as a ByteArray.
     */
    override fun writeSlot(value: SlotData) {
        when (value) {
            EmptySlot -> writeBoolean(false)
            is FilledSlot -> {
                writeVarInt(value.itemId)
                writeByte(value.itemCount)
                writeNBTTag(value.nbt ?: ENDTag)
            }
        }
    }
    
    /**
     * Writes a NbtTag as a ByteArray.
     */
    override fun writeNBTTag(value: NbtTag) {
        writeByte(value.type.id)
        writeNBTTagRaw(value)
    }
    
    private fun writeNBTTagRaw(value: NbtTag) {
        when (value) {
            is ByteTag -> writeByte(value.value)
            is ShortTag -> writeShort(value.value)
            is IntTag -> writeInt(value.value)
            is LongTag -> writeLong(value.value)
            is FloatTag -> writeFloat(value.value)
            is DoubleTag -> writeDouble(value.value)
            is ByteArrayTag -> {
                writeInt(value.value.size)
                writeByteArray(value.value)
            }
            is StringTag -> {
                val array = value.value.encodeToByteArray()
                writeUnsignedShort(array.size.toUShort())
                writeByteArray(array)
            }
            is ListTag -> {
                writeByte(value.typeId.id)
                writeInt(value.values.size)
                value.values.forEach { writeNBTTagRaw(it) }
            }
            is CompoundTag -> {
                value.map.forEach { (key, entry) ->
                    writeByte(entry.type.id)
                    val array = key.encodeToByteArray()
                    writeUnsignedShort(array.size.toUShort())
                    writeByteArray(array)
                    writeNBTTagRaw(entry)
                }
                writeByte(0)
            }
            is IntArrayTag -> {
                writeInt(value.value.size)
                value.value.forEach { writeInt(it) }
            }
            is LongArrayTag -> {
                writeInt(value.value.size)
                value.value.forEach { writeLong(it) }
            }
            is ENDTag -> {
            }
        }
    }
    
    /**
     * Writes a Position as a Long.
     */
    override fun writePosition(value: Position) =
        writeLong(value.x.toLong() and 0x3FFFFFF shl 38 or (value.z.toLong() and 0x3FFFFFF shl 12) or (value.y.toLong() and 0xFFF))
    
    /**
     * Writes a UUID as two Longs.
     */
    override fun writeUUID(value: UUID) {
        writeLong(value.upper)
        writeLong(value.lower)
    }

}
