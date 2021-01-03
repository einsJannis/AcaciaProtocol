package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
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

    fun onlyIf(isPresent: () -> Boolean, setPresent: (Boolean) -> Unit = {}) =
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
