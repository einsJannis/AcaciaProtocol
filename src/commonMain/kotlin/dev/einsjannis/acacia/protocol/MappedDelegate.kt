package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import kotlin.reflect.KProperty

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
