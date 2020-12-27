package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.chat.ChatComponent

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
    fun readEntityMetadata(): Any // TODO
    fun readSlot(): Any// TODO
    fun readNBTTag(): Any // TODO
    fun readPosition(): Position
    fun readUUID(): UUID // TODO
    fun readXEnum(): Any // TODO
    fun readByteArray(size: Int): ByteArray
}
