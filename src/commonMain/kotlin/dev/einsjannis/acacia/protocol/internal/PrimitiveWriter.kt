package dev.einsjannis.acacia.protocol.internal

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
    fun writeChat(value: Any) // TODO
    fun writeIdentifier(value: Any) // TODO
    fun writeVarInt(value: Int)
    fun writeVarLong(value: Long)
    fun writeEntityMetadata(value: Any) // TODO
    fun writeSlot(value: Any)// TODO
    fun writeNBTTag(value: Any) // TODO
    fun writePosition(value: Any) // TODO
    fun writeAngle(value: Any) // TODO
    fun writeUUID(value: Any) // TODO
    fun writeOptionalX(size: Int)
    fun beginArray(count: Int, size: Int)
    fun writeXEnum(value: Any) // TODO
    fun writeByteArray(value: ByteArray)
}
