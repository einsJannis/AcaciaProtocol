package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.primitives.*
import kotlin.reflect.KMutableProperty0

/**
 * Base Class for packet structures.
 */
abstract class PacketObject {

    /**
     * Delegates of all properties of the [PacketObject].
     */
    val delegates: List<BaseDelegate<*>>
        get() = _delegates.map {
            var temp = it
            while (temp.child != null) {
                temp = temp.child!!
            }
            temp
        }

    private val _delegates = mutableListOf<BaseDelegate<*>>()

    protected fun <T : BaseDelegate<E>, E> delegate(d: T): T {
        _delegates.add(d)
        return d
    }

    /**
     * Adds a Boolean delegate to the [PacketObject].
     */
    protected fun bool() = delegate(BooleanDelegate())

    /**
     * Adds a Byte delegate to the [PacketObject].
     */
    protected fun byte() = delegate(ByteDelegate())

    /**
     * Adds a UByte delegate to the [PacketObject].
     */
    protected fun ubyte() = delegate(UByteDelegate())

    /**
     * Adds a Short delegate to the [PacketObject].
     */
    protected fun short() = delegate(ShortDelegate())

    /**
     * Adds a UShort delegate to the [PacketObject].
     */
    protected fun ushort() = delegate(UShortDelegate())

    /**
     * Adds a Integer delegate to the [PacketObject].
     */
    protected fun int() = delegate(IntDelegate())

    /**
     * Adds a Long delegate to the [PacketObject].
     */
    protected fun long() = delegate(LongDelegate())

    /**
     * Adds a Float delegate to the [PacketObject].
     */
    protected fun float() = delegate(FloatDelegate())

    /**
     * Adds a Double delegate to the [PacketObject].
     */
    protected fun double() = delegate(DoubleDelegate())

    /**
     * Adds a String delegate to the [PacketObject].
     */
    protected fun string() = delegate(StringDelegate())

    /**
     * Adds a VarInt delegate to the [PacketObject].
     */
    protected fun varInt() = delegate(VarIntDelegate())

    /**
     * Adds a VarLong delegate to the [PacketObject].
     */
    protected fun varLong() = delegate(VarLongDelegate())

    /**
     * Adds a ByteArray delegate from a property to the [PacketObject].
     */
    protected fun byteArray(sizeProp: KMutableProperty0<Int>) =
        delegate(ByteArrayDelegate({ sizeProp.get() }, sizeProp::set))

    /**
     * Adds a ByteArray delegate from a getSize and setSize function to the [PacketObject].
     */
    protected fun byteArray(getSize: (remainingBytes: Int) -> Int, setSize: (Int) -> Unit) =
        delegate(ByteArrayDelegate(getSize, setSize))

    /**
     * Adds a UUID delegate to the [PacketObject].
     */
    protected fun uuid() = delegate(UUIDDelegate())

    /**
     * Adds a Chat delegate to the [PacketObject].
     */
    protected fun chat() = delegate(ChatDelegate())

    /**
     * Adds a Identifier delegate to the [PacketObject].
     */
    protected fun id() = delegate(IdentifierDelegate())

    /**
     * Adds a Position delegate to the [PacketObject].
     */
    protected fun position() = delegate(PositionDelegate())

    /**
     * Adds a Angle delegate to the [PacketObject].
     */
    protected fun angle() = delegate(AngleDelegate())

    /**
     * Adds a NbtTag delegate to the [PacketObject].
     */
    protected fun nbtTag() = delegate(NbtTagDelegate())

    /**
     * Adds a Slot delegate to the [PacketObject].
     */
    protected fun slot() = delegate(SlotDelegate())

    /**
     * Adds a EntityMetadata delegate to the [PacketObject].
     */
    protected fun entityMetadata() = delegate(EntityMetadataDelegate())

    /**
     * Adds a [PacketObject] delegate to the [PacketObject].
     */
    protected fun <T : PacketObject> `object`(objectConstructor: () -> T) =
        delegate(ObjectDelegate(objectConstructor))
    
    val isValid: Boolean get() = delegates.all { it.isValid }
    
}
