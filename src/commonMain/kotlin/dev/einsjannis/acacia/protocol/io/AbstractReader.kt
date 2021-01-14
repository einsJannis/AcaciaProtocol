package dev.einsjannis.acacia.protocol.io

import dev.einsjannis.UUID
import dev.einsjannis.acacia.protocol.primitives.Identifier
import dev.einsjannis.acacia.protocol.primitives.chat.ChatComponent
import dev.einsjannis.acacia.protocol.primitives.chat.ChatSerializer
import dev.einsjannis.acacia.protocol.primitives.nbt.*
import dev.einsjannis.acacia.protocol.types.Position
import dev.einsjannis.acacia.protocol.types.entity.*
import dev.einsjannis.runMultiplatformBlocking
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.experimental.and

/**
 * Abstract implementation of [PrimitiveReader]
 */
abstract class AbstractReader(override val scope: CoroutineScope) : PrimitiveReader {
    
    /**
     * Reads a Byte as a Boolean.
     */
    override fun readBoolean(): Boolean = when (readByte()) {
        0x01.toByte() -> true
        0x00.toByte() -> false
        else -> throw IllegalStateException()
    }
    
    /**
     * Reads a ByteArray as a Byte.
     */
    override fun readByte(): Byte = readByteArray(1).first()
    
    /**
     * Reads a Byte as a UByte.
     */
    override fun readUnsignedByte(): UByte = readByte().toUByte()
    
    /**
     * Reads a ByteArray as a Short.
     */
    override fun readShort(): Short {
        val array = readByteArray(2)
        return (array[0].toInt() shl 8 or array[1].toInt()).toShort()
    }
    
    /**
     * Reads a Short as a UShort.
     */
    override fun readUnsignedShort(): UShort = readShort().toUShort()
    
    /**
     * Reads a ByteArray as a Int.
     */
    override fun readInt(): Int {
        val array = readByteArray(4)
        return (array[0].toInt() shl 24) or
            (array[1].toInt() shl 16) or
            (array[2].toInt() shl 8) or
            array[3].toInt()
    }
    
    /**
     * Reads a ByteArray as a Long.
     */
    override fun readLong(): Long {
        val array = readByteArray(4)
        return (array[0].toLong() shl 56) or
            (array[1].toLong() shl 48) or
            (array[2].toLong() shl 40) or
            (array[3].toLong() shl 32) or
            (array[4].toLong() shl 24) or
            (array[5].toLong() shl 16) or
            (array[6].toLong() shl 8) or
            array[7].toLong()
    }
    
    /**
     * Reads a Int as a Float.
     */
    override fun readFloat(): Float = Float.fromBits(readInt())
    
    /**
     * Reads a Long as a Double.
     */
    override fun readDouble(): Double = Double.fromBits(readLong())
    
    /**
     * Reads a VarInt and a ByteArray as a String.
     */
    override fun readString(): String = readByteArray(readVarInt()).decodeToString() //TODO: int is character amount, fix this!
    
    /**
     * Reads a String as a ChatComponent.
     */
    override fun readChat(): ChatComponent = ChatSerializer.fromString(readString())
    
    /**
     * Reads a String as a Identifier
     */
    override fun readIdentifier(): Identifier = Identifier.from(readString())
    
    /**
     * Reads Bytes as a Int.
     */
    override fun readVarInt(): Int =
        scope.runMultiplatformBlocking { readVarInt(::readByte) }
    
    /**
     * Reads Bytes as a Long.
     */
    override fun readVarLong(): Long {
        var numRead = 0
        var result: Long = 0
        var read: Byte
        do {
            read = readByte()
            val value = (read and 127).toLong()
            result = result or (value shl 7 * numRead)
            numRead++
            if (numRead > 10) {
                throw RuntimeException("VarLong is too big")
            }
        } while (read and 128.toByte() != 0.toByte())
        return result
    }
    
    /**
     * Reads a ByteArray as Entity Metadata.
     */
    override fun readEntityMetadata(): List<EntityDataField> = buildList {
        while (true) {
            val index = readUnsignedByte().toInt()
            if (index == 0xFF) break
            val type = EntityMetadataType.values()[readVarInt()]
            val obj: EntityField = when (type) {
                EntityMetadataType.BYTE -> ByteEntityField()
                EntityMetadataType.VARINT -> VarIntEntityField()
                EntityMetadataType.FLOAT -> FloatEntityField()
                EntityMetadataType.STRING -> StringEntityField()
                EntityMetadataType.CHAT -> ChatEntityField()
                EntityMetadataType.OPTCHAT -> OptionalChatEntityField()
                EntityMetadataType.SLOT -> SlotEntityField()
                EntityMetadataType.BOOLEAN -> BooleanEntityField()
                EntityMetadataType.ROTATION -> RotationEntityField()
                EntityMetadataType.POSITION -> PositionEntityField()
                EntityMetadataType.OPTPOSITION -> OptionalPositionEntityField()
                EntityMetadataType.DIRECTION -> DirectionEntityField()
                EntityMetadataType.OPTUUID -> OptionalUUIDEntityField()
                EntityMetadataType.OPTBLOCKID -> OptionalBlockIdEntityField()
                EntityMetadataType.NBT -> NbtEntityField()
                EntityMetadataType.PARTICLE -> ParticleEntityField()
                EntityMetadataType.VILLAGER_DATA -> VillagerDataEntityField()
                EntityMetadataType.OPTVARINT -> OptionalVarIntEntityField()
                EntityMetadataType.POSE -> PoseEntityField()
            }
            obj.delegates.forEach { it.readValue(this@AbstractReader) }
            add(EntityDataField(index, obj))
        }
    }
    
    /**
     * Reads a ByteArray as a Slot
     */
    override fun readSlot(): SlotData = if (readBoolean()) {
        val itemId = readVarInt()
        val itemCount = readByte()
        var nbt: NbtTag? = readNBTTag()
        if (nbt == ENDTag)
            nbt = null
        FilledSlot(itemId, itemCount, nbt)
    } else EmptySlot
    
    /**
     * Reads a ByteArray as a NBTTag.
     */
    override fun readNBTTag(): NbtTag = readNBTTag(NbtTypeId.values()[readByte().toInt()])
    private fun readNBTTag(type: NbtTypeId): NbtTag = when (type) {
        NbtTypeId.END -> ENDTag
        NbtTypeId.BYTE -> ByteTag(readByte())
        NbtTypeId.SHORT -> ShortTag(readShort())
        NbtTypeId.INT -> IntTag(readInt())
        NbtTypeId.LONG -> LongTag(readLong())
        NbtTypeId.FLOAT -> FloatTag(readFloat())
        NbtTypeId.DOUBLE -> DoubleTag(readDouble())
        NbtTypeId.BYTE_ARRAY -> ByteArrayTag(readByteArray(readInt()))
        NbtTypeId.STRING -> StringTag(readByteArray(readInt()).decodeToString())
        NbtTypeId.LIST -> {
            val subTypeId = readByte()
            val subType = NbtTypeId.values()[subTypeId.toInt()]
            val length = readInt()
            ListTag(subType, buildList(length) {
                repeat((0 until length).count()) {
                    readNBTTag(subType)
                }
            })
        }
        NbtTypeId.COMPOUND -> {
            val map = mutableMapOf<String, NbtTag>()
            while (true) {
                val subType = NbtTypeId.values()[readByte().toInt()]
                if (subType == NbtTypeId.END) break
                val name = readByteArray(readInt()).decodeToString()
                val nbt = readNBTTag(subType)
                map[name] = nbt
            }
            CompoundTag(map)
        }
        NbtTypeId.INT_ARRAY -> IntArrayTag(IntArray(readInt()) { readInt() })
        NbtTypeId.LONG_ARRAY -> LongArrayTag(LongArray(readInt()) { readLong() })
    }
    
    /**
     * Reads a Long as a Position.
     */
    override fun readPosition(): Position {
        val value = readLong()
        return Position((value ushr 38).toInt(), (value and 0xFFF).toInt(), (value shl 26 ushr 38).toInt())
    }
    
    /**
     * Reads two Longs as a UUID.
     */
    override fun readUUID(): UUID = UUID(readLong(), readLong())
    
}
