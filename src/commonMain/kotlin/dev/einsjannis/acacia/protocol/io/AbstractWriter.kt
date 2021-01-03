package dev.einsjannis.acacia.protocol.io

import dev.einsjannis.acacia.protocol.primitives.Identifier
import dev.einsjannis.acacia.protocol.primitives.UUID
import dev.einsjannis.acacia.protocol.primitives.chat.ChatComponent
import dev.einsjannis.acacia.protocol.primitives.chat.ChatSerializer
import dev.einsjannis.acacia.protocol.primitives.nbt.*
import dev.einsjannis.acacia.protocol.types.Position
import dev.einsjannis.acacia.protocol.types.entity.*
import kotlin.experimental.or

abstract class AbstractWriter : PrimitiveWriter {

    override fun writeBoolean(value: Boolean) = writeByte(if (value) 0x01 else 0x00)

    override fun writeByte(value: Byte) = writeByteArray(byteArrayOf(value))

    override fun writeUnsignedByte(value: UByte) = writeByte(value.toByte())

    override fun writeShort(value: Short) = writeByteArray(
        byteArrayOf(
            (value.toInt() shr 8).toByte(),
            value.toByte()
        )
    )

    override fun writeUnsignedShort(value: UShort) = writeShort(value.toShort())

    override fun writeInt(value: Int) = writeByteArray(
        byteArrayOf(
            (value shr 24).toByte(),
            (value shr 16).toByte(),
            (value shr 8).toByte(),
            value.toByte()
        )
    )

    override fun writeLong(value: Long) = writeByteArray(
        byteArrayOf(
            (value shr 56).toByte(),
            (value shr 48).toByte(),
            (value shr 40).toByte(),
            (value shr 32).toByte(),
            (value shr 24).toByte(),
            (value shr 16).toByte(),
            (value shr 8).toByte(),
            value.toByte()
        )
    )

    override fun writeFloat(value: Float) = writeInt(value.toRawBits())

    override fun writeDouble(value: Double) = writeLong(value.toRawBits())

    override fun writeString(value: String) {
        val array = value.encodeToByteArray()
        writeVarInt(array.size)
        writeByteArray(array)
    }

    override fun writeChat(value: ChatComponent) = writeString(ChatSerializer.toString(value))

    override fun writeIdentifier(value: Identifier) = writeString(value.toString())

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

    override fun writeVarLong(value: Long) {
        var work = value
        do {
            var temp = (work and 0b01111111).toByte()
            work = work ushr 7
            if (work != 0L) temp = temp or 0b10000000.toByte()
            writeByte(temp)
        } while (work != 0L)
    }

    override fun writeEntityMetadata(value: List<EntityDataField>) {
        value.forEach {
            writeUnsignedByte(it.index.toUByte())
            writeVarInt(it.data.type.typeId)
            it.data.delegates.forEach { it.writeValue(this) }
        }
        writeUnsignedByte(0xFF.toUByte())
    }

    override fun writeSlot(value: SlotData) {
        when (value) {
            EmptySlot -> writeBoolean(false)
            is FilledSlot -> {
                writeVarInt(value.itemId)
                writeByte(value.itemCount)
                if (value.nbt != null)
                    writeNBTTag(value.nbt!!)
                else writeByte(0)
            }
        }
    }

    override fun writeNBTTag(value: NbtTag) {
        writeByte(value.type.id)
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
                value.values.forEach { writeNBTTag(it) }
            }
            is CompoundTag -> {
                value.map.forEach { (key, entry) ->
                    val array = key.encodeToByteArray()
                    writeUnsignedShort(array.size.toUShort())
                    writeByteArray(array)
                    writeNBTTag(entry)
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
        }
    }

    override fun writePosition(value: Position) =
        writeLong(value.x.toLong() and 0x3FFFFFF shl 38 or (value.z.toLong() and 0x3FFFFFF shl 12) or (value.y.toLong() and 0xFFF))

    override fun writeUUID(value: UUID) {
        writeLong(value.upper)
        writeLong(value.lower)
    }

}
