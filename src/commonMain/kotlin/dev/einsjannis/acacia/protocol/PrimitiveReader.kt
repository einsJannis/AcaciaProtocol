package dev.einsjannis.acacia.protocol

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
    fun readChat(): Any // TODO
    fun readIdentifier(): Any // TODO
    fun readVarInt(): Int
    fun readVarLong(): Long
    fun readEntityMetadata(): Any // TODO
    fun readSlot(): Any// TODO
    fun readNBTTag(): Any // TODO
    fun readPosition(): Any // TODO
    fun readAngle(): Any // TODO
    fun readUUID(): UUID // TODO
    fun readXEnum(): Any // TODO
    fun readByteArray(size: Int): ByteArray
}
