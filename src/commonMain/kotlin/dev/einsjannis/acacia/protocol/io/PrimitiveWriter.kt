package dev.einsjannis.acacia.protocol.io

import dev.einsjannis.UUID
import dev.einsjannis.acacia.protocol.primitives.Identifier
import dev.einsjannis.acacia.protocol.primitives.chat.ChatComponent
import dev.einsjannis.acacia.protocol.types.entity.EntityDataField
import dev.einsjannis.acacia.protocol.primitives.nbt.NbtTag
import dev.einsjannis.acacia.protocol.types.Position
import dev.einsjannis.acacia.protocol.primitives.nbt.SlotData
import kotlinx.coroutines.CoroutineScope

/**
 * Declaration of functions to write primitives of the minecraft protocol.
 */
interface PrimitiveWriter {
    
    val scope: CoroutineScope
    
    /**
     * Writes a Boolean. True should be encoded as 0x01 and flase should be encoded as 0x00.
     */
    fun writeBoolean(value: Boolean)
    
    /**
     * Writes a Byte. A signed 8-bit integer (with the range -128 to 127) (two complement).
     */
    fun writeByte(value: Byte)
    
    /**
     * Writes an UByte. An unsigned 8-bit integer (with the range 0 to 256).
     */
    fun writeUnsignedByte(value: UByte)
    
    /**
     * Writes a Short. A signed 16-bit integer (with the range 32768 to 32767) (two complement).
     */
    fun writeShort(value: Short)
    
    /**
     * Writes a UShort. An unsigned 16-bit integer (with the range 0 to 65535).
     */
    fun writeUnsignedShort(value: UShort)
    
    /**
     * Writes a Int. A signed 32-bit integer (with the range -2147483648 to 2147483647) (two complement).
     */
    fun writeInt(value: Int)
    
    /**
     * Writes a Long. A signed 64-bit integer (with the range -9223372036854775808 to 9223372036854775807)
     * (two complement).
     */
    fun writeLong(value: Long)
    
    /**
     * Writes a Float. A single-precision 32-bit IEEE 754 floating point number.
     */
    fun writeFloat(value: Float)
    
    /**
     * Writes a Double. A double-precision 64-bit IEEE 754 floating point number.
     */
    fun writeDouble(value: Double)
    
    /**
     * Writes a String. A sequence of Unicode scalar values (UTF-8 encoded) prefixed with a its character count encoded
     * as a VarInt.
     */
    fun writeString(value: String)
    
    /**
     * Writes a ChatComponent. A json chat object encoded as a string.
     */
    fun writeChat(value: ChatComponent)
    
    /**
     * Writes an Identifier. A string containing a name and maybe an additional namespace.
     */
    fun writeIdentifier(value: Identifier)
    
    /**
     * Writes an Int. Variable-length data encoding a two's complement signed 32-bit integer.
     */
    fun writeVarInt(value: Int)
    
    /**
     * Writes a Long. Variable-length data encoding a two's complement signed 64-bit integer.
     */
    fun writeVarLong(value: Long)
    
    /**
     * Writes Entity Metadata.
     */
    fun writeEntityMetadata(value: List<EntityDataField>)
    
    /**
     * Writes SlotData.
     */
    fun writeSlot(value: SlotData)
    
    /**
     * Writes a NbtTag.
     */
    fun writeNBTTag(value: NbtTag)
    
    /**
     * Writes a Position. x as a 26-bit integer, followed by y as a 12-bit integer, followed by z as a 26-bit integer
     * (all signed, two's complement).
     */
    fun writePosition(value: Position)
    
    /**
     * Writes a UUID. Encoded as a 128-bit integer.
     */
    fun writeUUID(value: UUID)
    
    /**
     * Writes a ByteArray. An array of bytes.
     */
    fun writeByteArray(value: ByteArray)
    
}
