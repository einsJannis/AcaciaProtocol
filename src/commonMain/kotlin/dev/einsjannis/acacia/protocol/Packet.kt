package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.chat.ChatComponent
import dev.einsjannis.acacia.protocol.entity.EntityDataField
import dev.einsjannis.acacia.protocol.nbt.NbtTag
import dev.einsjannis.acacia.protocol.nbt.SlotData
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty

abstract class BaseDelegate<T> : ReadWriteProperty<PacketObject, T> {

    private var _value: T? = null

    open fun readValue(reader: PrimitiveReader) {
        _value = read(reader)
    }

    open fun writeValue(writer: PrimitiveWriter) {
        // TODO: warning or exception here?
        _value?.let { write(writer, it) }
    }

    override fun setValue(thisRef: PacketObject, property: KProperty<*>, value: T) {
        _value = value
    }

    override fun getValue(thisRef: PacketObject, property: KProperty<*>): T {
        return _value!!
    }

    fun onlyIf(boolProperty: KMutableProperty0<Boolean>) =
        OptionalDelegate(this, boolProperty::get, boolProperty::set)

    fun onlyIf(isPresent: () -> Boolean, setPresent: (Boolean) -> Unit) =
        OptionalDelegate(this, isPresent, setPresent)

    fun array(sizeProp: KMutableProperty0<Int>) =
        ArrayDelegate(this, { sizeProp.get() }, sizeProp::set)

    fun array(getSize: (remainingBytes: Int) -> Int, setSize: (Int) -> Unit) =
        ArrayDelegate(this, getSize, setSize)

    fun <V> mapped(from: (T) -> V, to: (V) -> T): MappedDelegate<V, T> = MappedDelegate(this, from, to)
    fun <V> mapped(fromMap: Map<T, V>, toMap: Map<V, T>): MappedDelegate<V, T> =
        mapped({ fromMap[it]!! }, { toMap[it]!! })

    fun <V> mapped(values: Map<T, V>): MappedDelegate<V, T> = mapped(values, values.map { (k, v) -> v to k }.toMap())


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

class ByteArrayDelegate(val getSize: (remainingBytes: Int) -> Int, val setSize: (Int) -> Unit) : BaseDelegate<ByteArray>() {
    override fun setValue(thisRef: PacketObject, property: KProperty<*>, value: ByteArray) {
        super.setValue(thisRef, property, value)
        setSize(value.size)
    }
    override fun read(reader: PrimitiveReader): ByteArray = reader.readByteArray(getSize(reader.remainingBytes))
    override fun write(writer: PrimitiveWriter, value: ByteArray) = writer.writeByteArray(value)
}

class UUIDDelegate : BaseDelegate<UUID>() {
    override fun read(reader: PrimitiveReader): UUID = reader.readUUID()
    override fun write(writer: PrimitiveWriter, value: UUID) = writer.writeUUID(value)
}

class ChatDelegate : BaseDelegate<ChatComponent>() {
    override fun read(reader: PrimitiveReader): ChatComponent = reader.readChat()
    override fun write(writer: PrimitiveWriter, value: ChatComponent) = writer.writeChat(value)
}

class IdentifierDelegate : BaseDelegate<Identifier>() {
    override fun read(reader: PrimitiveReader): Identifier = reader.readIdentifier()
    override fun write(writer: PrimitiveWriter, value: Identifier) = writer.writeIdentifier(value)
}

class PositionDelegate : BaseDelegate<Position>() {
    override fun read(reader: PrimitiveReader): Position = reader.readPosition()
    override fun write(writer: PrimitiveWriter, value: Position) = writer.writePosition(value)
}

class AngleDelegate : BaseDelegate<UByte>() {
    override fun read(reader: PrimitiveReader): UByte = reader.readUnsignedByte()
    override fun write(writer: PrimitiveWriter, value: UByte) = writer.writeUnsignedByte(value)
}

class NbtTagDelegate : BaseDelegate<NbtTag>() {
    override fun read(reader: PrimitiveReader): NbtTag = reader.readNBTTag()
    override fun write(writer: PrimitiveWriter, value: NbtTag) = writer.writeNBTTag(value)
}

class SlotDelegate : BaseDelegate<SlotData>() {
    override fun read(reader: PrimitiveReader): SlotData = reader.readSlot()
    override fun write(writer: PrimitiveWriter, value: SlotData) = writer.writeSlot(value)
}

class EntityMetadataDelegate : BaseDelegate<List<EntityDataField>>() {
    override fun read(reader: PrimitiveReader): List<EntityDataField> = reader.readEntityMetadata()
    override fun write(writer: PrimitiveWriter, value: List<EntityDataField>) = writer.writeEntityMetadata(value)
}

class OptionalDelegate<T>(
    val elementDelegate: BaseDelegate<T>,
    val isPresent: () -> Boolean,
    val setPresent: (Boolean) -> Unit
) : BaseDelegate<T?>() {
    override fun setValue(thisRef: PacketObject, property: KProperty<*>, value: T?) {
        super.setValue(thisRef, property, value)
        setPresent(value != null)
    }
    override fun read(reader: PrimitiveReader): T? = if (isPresent()) elementDelegate.read(reader) else null
    override fun write(writer: PrimitiveWriter, value: T?): Unit =
        value?.let { elementDelegate.write(writer, it) } ?: Unit
}

class ArrayDelegate<T>(val elementDelegate: BaseDelegate<T>, val getSize: (remainingBytes: Int) -> Int, val setSize: (Int) -> Unit) :
    BaseDelegate<List<T>>() {
    override fun setValue(thisRef: PacketObject, property: KProperty<*>, value: List<T>) {
        super.setValue(thisRef, property, value)
        setSize(value.size)
    }

    override fun read(reader: PrimitiveReader): List<T> = (0 until getSize(reader.remainingBytes)).map { elementDelegate.read(reader) }
    override fun write(writer: PrimitiveWriter, value: List<T>) =
        value.forEach { elementDelegate.write(writer, it) }
}

class MappedDelegate<T, V>(val wrappedDelegate: BaseDelegate<V>, val from: (V) -> T, val to: (T) -> V) :
    BaseDelegate<T>() {
    override fun setValue(thisRef: PacketObject, property: KProperty<*>, value: T) {
        wrappedDelegate.setValue(thisRef, property, to(value))
    }

    override fun getValue(thisRef: PacketObject, property: KProperty<*>): T =
        from(wrappedDelegate.getValue(thisRef, property))

    override fun readValue(reader: PrimitiveReader) {
        wrappedDelegate.readValue(reader)
    }

    override fun writeValue(writer: PrimitiveWriter) {
        wrappedDelegate.writeValue(writer)
    }

    override fun write(writer: PrimitiveWriter, value: T) {
        throw UnsupportedOperationException()
    }

    override fun read(reader: PrimitiveReader): T {
        throw UnsupportedOperationException()
    }
}

inline fun <reified T : Enum<T>> BaseDelegate<Int>.enumOrdinalMapping() =
    mapped({ enumValues<T>()[it] }, { it.ordinal })

class ObjectDelegate<T : PacketObject>(val packetObjectConstructor: () -> T) : BaseDelegate<T>() {
    override fun read(reader: PrimitiveReader): T = packetObjectConstructor()
        .also { it.delegates.forEach { it.readValue(reader) } }

    override fun write(writer: PrimitiveWriter, value: T) = value.delegates.forEach { it.writeValue(writer) }
}

enum class Bound {
    CLIENT,
    SERVER
}

enum class ConnectionState {
    HANDSHAKE,
    STATUS,
    LOGIN,
    PLAY
}

abstract class PacketMeta<T : Packet>(val id: Int, val connectionState: ConnectionState, val bound: Bound, val constrctor: () -> T) {

    fun readPacket(primitiveReader: PrimitiveReader): T {
        val packet = constrctor()
        packet.delegates.forEach { it.readValue(primitiveReader) }
        return packet
    }

    fun writePacket(primitiveWriter: PrimitiveWriter, packet: T) {
        packet.delegates.forEach { it.writeValue(primitiveWriter) }
    }

}

abstract class Packet : PacketObject()

abstract class PacketObject {

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
        delegate(ByteArrayDelegate({ sizeProp.get() }, sizeProp::set))
    fun byteArray(getSize: (remainingBytes: Int) -> Int, setSize: (Int) -> Unit) =
        delegate(ByteArrayDelegate(getSize, setSize))
    fun uuid() = delegate(UUIDDelegate())
    fun chat() = delegate(ChatDelegate())
    fun id() = delegate(IdentifierDelegate())
    fun position() = delegate(PositionDelegate())
    fun angle() = delegate(AngleDelegate())
    fun nbtTag() = delegate(NbtTagDelegate())
    fun slot() = delegate(SlotDelegate())
    fun entityMetadata() = delegate(EntityMetadataDelegate())
    fun <T : PacketObject> `object`(objectConstructor: () -> T) =
        delegate(ObjectDelegate(objectConstructor))
}
