package dev.einsjannis.acacia.protocol.io

import dev.einsjannis.UUID
import dev.einsjannis.acacia.protocol.primitives.Identifier
import dev.einsjannis.acacia.protocol.primitives.chat.ChatComponent
import dev.einsjannis.acacia.protocol.types.entity.EntityDataField
import dev.einsjannis.acacia.protocol.primitives.nbt.NbtTag
import dev.einsjannis.acacia.protocol.types.Position
import dev.einsjannis.acacia.protocol.primitives.nbt.SlotData

/**
 * Defines functions used to read the minecraft protocol primitive types
 */
interface PrimitiveReader {
    
    /**
     * Reads a Boolean. True should be encoded as 0x01 and flase should be encoded as 0x00
     */
    fun readBoolean(): Boolean
    
    /**
     * Reads a Byte. A signed 8-bit integer (with the range -128 to 127) (two complement)
     */
    fun readByte(): Byte
    
    /**
     * Reads an UByte. An unsigned 8-bit integer (with the range 0 to 256)
     */
    fun readUnsignedByte(): UByte
    
    /**
     * Reads a Short. A signed 16-bit integer (with the range -32768 to 32767) (two complement)
     */
    fun readShort(): Short
    
    /**
     * Reads a UShort. An unsigned 16-bit integer (with the range 0 to 65535)
     */
    fun readUnsignedShort(): UShort
    
    /**
     * Reads an Int. A signed 32-bit integer (with the range -2147483648 to 2147483647) (two complement)
     */
    fun readInt(): Int
    
    /**
     * Reads a Long. A signed 64-bit integer (with the range -9223372036854775808 to 9223372036854775807)
     * (two complement)
     */
    fun readLong(): Long
    
    /**
     * Reads a Float. A single-precision 32-bit IEEE 754 floating point number.
     */
    fun readFloat(): Float
    
    /**
     * Reads a Double. A double-precision 64-bit IEEE 754 floating point number
     */
    fun readDouble(): Double
    
    /**
     * Reads a String. A sequence of Unicode scalar values (UTF-8 encoded) prefixed with a its character count encoded
     * as a VarInt.
     */
    fun readString(): String
    
    /**
     * Reads a ChatComponent. A json chat object encoded as a string.
     */
    fun readChat(): ChatComponent
    
    /**
     * Reads a Identifier. A string containing a name and maybe an additional namespace.
     */
    fun readIdentifier(): Identifier
    
    /**
     * Reads an Int. A variable-length data encoded, two's complement signed 32-bit integer.
     */
    fun readVarInt(): Int
    
    /**
     * Reads a Long. A variable-length data encoded, two's complement signed 64-bit integer.
     */
    fun readVarLong(): Long
    
    /**
     * Reads Entity Metadata.
     */
    fun readEntityMetadata(): List<EntityDataField>
    
    /**
     * Reads Slot Data.
     */
    fun readSlot(): SlotData
    
    /**
     * Reads an NBTTag.
     */
    fun readNBTTag(): NbtTag
    
    /**
     * Reads a Position. x as a 26-bit integer, followed by y as a 12-bit integer, followed by z as a 26-bit integer
     * (all signed, two's complement).
     */
    fun readPosition(): Position
    
    /**
     * Reads a UUID. Encoded as a 128-bit integer.
     */
    fun readUUID(): UUID
    
    /**
     * Reads a ByteArray. An array of bytes.
     */
    fun readByteArray(size: Int): ByteArray
    
    /**
     * Amount of remaining bytes in packet.
     */
    val remainingBytes: Int
    
}
