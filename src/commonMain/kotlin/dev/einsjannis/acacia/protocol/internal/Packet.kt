package dev.einsjannis.acacia.protocol.internal

abstract class BaseDelegate<T> : ReadWriteProperty<Packet, T> {
    private var _value: T

    fun readValue(reader: PrimitiveReader) {
        _value = read(reader)
    }
    fun writeValue(writer: PrimitiveWriter) {
        write(writer, _value)
    }

    override fun setValue(thisRef: Packet, property: KProperty)

    fun array(sizeProp: KProperty<Any?, Int>) = ArrayDelegate(this, sizeProp)
    abstract fun write(writer: PrimitiveWriter, value: T)
    abstract fun read(reader: PrimitivieReader): T
}

class BooleanDelegate : BaseDelegate<Boolean>()
class ArrayDelegate<T>(val elementDelegate: BaseDelegate<T>, val sizeProp: KProperty<Any?, Int>) :
    BaseDelegate<Array<T>>() {

}

abstract class Packet {

    private val delegates = mutableListOf<BaseDelegate<*>>()

    private fun <T : BaseDelegate<E>, E> delegate(d: T): T {
        delegates.add(d)
        return d
    }

    fun bool() = delegate(BooleanDelegate())
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

class ChunkData : Packet() {
    var x by int()
    var z by int()
    var fullChunk by bool()
    var primaryBitMask by varInt()
    var heightMaps by nbt()
    var biomeLength by varInt().onlyIf { fullChunk }
    var biomes by varInt().array { biomeLength }.onlyIf { fullChunk }
    var size by varInt()
    var data by byteArray { size }
    var blockEntitiyCount by varInt()
    var block Entities by nbt().array(
    { blockEntityCount },
    { blockEntityCount = value })
}
