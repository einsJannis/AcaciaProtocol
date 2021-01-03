package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.primitives.*
import kotlin.reflect.KMutableProperty0

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
