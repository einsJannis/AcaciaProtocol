package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.primitives.*
import kotlin.reflect.KMutableProperty0

abstract class PacketObject {

    val delegates: List<BaseDelegate<*>>
        get() = _delegates.map {
            var temp = it
            while (temp.child != null) {
                temp = temp.child!!
            }
            temp
        }

    private val _delegates = mutableListOf<BaseDelegate<*>>()

    private fun <T : BaseDelegate<E>, E> delegate(d: T): T {
        _delegates.add(d)
        return d
    }

    protected fun bool() = delegate(BooleanDelegate())
    protected fun byte() = delegate(ByteDelegate())
    protected fun ubyte() = delegate(UByteDelegate())
    protected fun short() = delegate(ShortDelegate())
    protected fun ushort() = delegate(UShortDelegate())
    protected fun int() = delegate(IntDelegate())
    protected fun long() = delegate(LongDelegate())
    protected fun float() = delegate(FloatDelegate())
    protected fun double() = delegate(DoubleDelegate())
    protected fun string() = delegate(StringDelegate())
    protected fun varInt() = delegate(VarIntDelegate())
    protected fun varLong() = delegate(VarLongDelegate())
    protected fun byteArray(sizeProp: KMutableProperty0<Int>) =
        delegate(ByteArrayDelegate({ sizeProp.get() }, sizeProp::set))
    protected fun byteArray(getSize: (remainingBytes: Int) -> Int, setSize: (Int) -> Unit) =
        delegate(ByteArrayDelegate(getSize, setSize))
    protected fun uuid() = delegate(UUIDDelegate())
    protected fun chat() = delegate(ChatDelegate())
    protected fun id() = delegate(IdentifierDelegate())
    protected fun position() = delegate(PositionDelegate())
    protected fun angle() = delegate(AngleDelegate())
    protected fun nbtTag() = delegate(NbtTagDelegate())
    protected fun slot() = delegate(SlotDelegate())
    protected fun entityMetadata() = delegate(EntityMetadataDelegate())
    protected fun <T : PacketObject> `object`(objectConstructor: () -> T) =
        delegate(ObjectDelegate(objectConstructor))
}
