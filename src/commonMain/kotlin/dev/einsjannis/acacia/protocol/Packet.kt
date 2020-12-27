package dev.einsjannis.acacia.protocol

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.*

abstract class BaseDelegate<T> : ReadWriteProperty<Packet, T> {

    private var _value: T? = null

    fun readValue(reader: PrimitiveReader) {
        _value = read(reader)
    }

    fun writeValue(writer: PrimitiveWriter) {
        _value?.let { write(writer, it) }
    }

    override fun setValue(thisRef: Packet, property: KProperty<*>, value: T) {
        _value = value
    }

    override fun getValue(thisRef: Packet, property: KProperty<*>): T {
        return _value!!
    }

    fun onlyIf(boolProperty: KMutableProperty0<Boolean>) =
        OptionalDelegate(this, boolProperty::get, boolProperty::set)

    fun onlyIf(isPresent: () -> Boolean, setPresent: (Boolean) -> Unit) =
        OptionalDelegate(this, isPresent, setPresent)

    fun array(sizeProp: KMutableProperty0<Int>) =
        ArrayDelegate(this, sizeProp::get, sizeProp::set)

    fun array(getSize: () -> Int, setSize: (Int) -> Unit) =
        ArrayDelegate(this, getSize, setSize)

    abstract fun write(writer: PrimitiveWriter, value: T)
    abstract fun read(reader: PrimitiveReader): T

}

class BooleanDelegate : BaseDelegate<Boolean>() {
    override fun read(reader: PrimitiveReader): Boolean = reader.readBoolean()
    override fun write(writer: PrimitiveWriter, value: Boolean) = writer.writeBoolean(value)
}

class ByteDelegate : BaseDelegate<Byte>() {
    override fun read(reader: PrimitiveReader): Byte = reader.readByte()
    override fun write(writer: PrimitiveWriter, value: Byte) = writer.writeByte(value)
}

class UByteDelegate : BaseDelegate<UByte>() {
    override fun read(reader: PrimitiveReader): UByte = reader.readUnsignedByte()
    override fun write(writer: PrimitiveWriter, value: UByte) = writer.writeUnsignedByte(value)
}

class ShortDelegate : BaseDelegate<Short>() {
    override fun read(reader: PrimitiveReader): Short = reader.readShort()
    override fun write(writer: PrimitiveWriter, value: Short) = writer.writeShort(value)
}

class UShortDelegate : BaseDelegate<UShort>() {
    override fun read(reader: PrimitiveReader): UShort = reader.readUnsignedShort()
    override fun write(writer: PrimitiveWriter, value: UShort) = writer.writeUnsignedShort(value)
}

class IntDelegate : BaseDelegate<Int>() {
    override fun read(reader: PrimitiveReader): Int = reader.readInt()
    override fun write(writer: PrimitiveWriter, value: Int) = writer.writeInt(value)
}

class LongDelegate : BaseDelegate<Long>() {
    override fun read(reader: PrimitiveReader): Long = reader.readLong()
    override fun write(writer: PrimitiveWriter, value: Long) = writer.writeLong(value)
}

class FloatDelegate : BaseDelegate<Float>() {
    override fun read(reader: PrimitiveReader): Float = reader.readFloat()
    override fun write(writer: PrimitiveWriter, value: Float) = writer.writeFloat(value)
}

class DoubleDelegate : BaseDelegate<Double>() {
    override fun read(reader: PrimitiveReader): Double = reader.readDouble()
    override fun write(writer: PrimitiveWriter, value: Double) = writer.writeDouble(value)
}

class StringDelegate : BaseDelegate<String>() {
    override fun read(reader: PrimitiveReader): String = reader.readString()
    override fun write(writer: PrimitiveWriter, value: String) = writer.writeString(value)
}

class VarIntDelegate : BaseDelegate<Int>() {
    override fun read(reader: PrimitiveReader): Int = reader.readVarInt()
    override fun write(writer: PrimitiveWriter, value: Int) = writer.writeVarInt(value)
}

class VarLongDelegate : BaseDelegate<Long>() {
    override fun read(reader: PrimitiveReader): Long = reader.readVarLong()
    override fun write(writer: PrimitiveWriter, value: Long) = writer.writeVarLong(value)
}

class ByteArrayDelegate(val getSize: () -> Int, val setSize: (Int) -> Unit) : BaseDelegate<ByteArray>() {
    override fun setValue(thisRef: Packet, property: KProperty<*>, value: ByteArray) {
        super.setValue(thisRef, property, value)
        setSize(value.size)
    }
    override fun read(reader: PrimitiveReader): ByteArray = reader.readByteArray(getSize())
    override fun write(writer: PrimitiveWriter, value: ByteArray) = writer.writeByteArray(value)
}

class UUIDDelegate : BaseDelegate<UUID>() {
    override fun read(reader: PrimitiveReader): UUID = reader.readUUID()
    override fun write(writer: PrimitiveWriter, value: UUID) = writer.writeUUID(value)
}

class OptionalDelegate<T>(
    val elementDelegate: BaseDelegate<T>,
    val isPresent: () -> Boolean,
    val setPresent: (Boolean) -> Unit
) : BaseDelegate<T?>() {
    override fun setValue(thisRef: Packet, property: KProperty<*>, value: T?) {
        super.setValue(thisRef, property, value)
        setPresent(value != null)
    }
    override fun read(reader: PrimitiveReader): T? = if (isPresent()) elementDelegate.read(reader) else null
    override fun write(writer: PrimitiveWriter, value: T?): Unit =
        value?.let { elementDelegate.write(writer, it) } ?: Unit
}

class ArrayDelegate<T>(val elementDelegate: BaseDelegate<T>, val getSize: () -> Int, val setSize: (Int) -> Unit) :
    BaseDelegate<List<T>>() {
    override fun setValue(thisRef: Packet, property: KProperty<*>, value: List<T>) {
        super.setValue(thisRef, property, value)
        setSize(value.size)
    }
    override fun read(reader: PrimitiveReader): List<T> = (0 until getSize()).map { elementDelegate.read(reader) }
    override fun write(writer: PrimitiveWriter, value: List<T>) =
        value.forEach { elementDelegate.write(writer, it) }
}

enum class ConnectionState {
    HANDSHAKE,
    STATUS,
    LOGIN,
    PLAY
}

abstract class PacketMeta<T : Packet>(val id: Int, val connectionState: ConnectionState, val constrctor: () -> T) {

    fun readPacket(primitiveReader: PrimitiveReader): T {
        val packet = constrctor()
        packet.delegates.forEach { it.readValue(primitiveReader) }
        return packet
    }

    fun writePacket(primitiveWriter: PrimitiveWriter, packet: T) {
        packet.delegates.forEach { it.writeValue(primitiveWriter) }
    }

}

abstract class Packet {

    internal val delegates = mutableListOf<BaseDelegate<*>>()

    private fun <T : BaseDelegate<E>, E> delegate(d: T): T {
        delegates.add(d)
        return d
    }

    fun bool() = delegate(BooleanDelegate())
    fun byte() = delegate(ByteDelegate())
    fun ubyte() = delegate(UByteDelegate())
    fun short() = delegate(ShortDelegate())
    fun ushort() = delegate(UShortDelegate())
    fun int() = delegate(IntDelegate())
    fun long() = delegate(LongDelegate())
    fun float() = delegate(FloatDelegate())
    fun double() = delegate(DoubleDelegate())
    fun string() = delegate(StringDelegate())
    fun varInt() = delegate(VarIntDelegate())
    fun varLong() = delegate(VarLongDelegate())
    fun byteArray(sizeProp: KMutableProperty0<Int>) =
        delegate(ByteArrayDelegate(sizeProp::get, sizeProp::set))
    fun byteArray(getSize: () -> Int, setSize: (Int) -> Unit) =
        delegate(ByteArrayDelegate(getSize, setSize))
    fun uuid() = delegate(UUIDDelegate())
    /*
    fun writeChat(value: Any) // TODO
    fun writeIdentifier(value: Any) // TODO
    fun writeEntityMetadata(value: Any) // TODO
    fun writeSlot(value: Any)// TODO
    fun writeNBTTag(value: Any) // TODO
    fun writePosition(value: Any) // TODO
    fun writeAngle(value: Any) // TODO
    fun writeUUID(value: Any) // TODO
    fun writeXEnum(value: Any) // TODO
    */
}

/*
class ChunkData : Packet() {
    var x by int()
    var b by bool()
    var z by int().onlyIf(::b)
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
*/
