package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import kotlin.reflect.KProperty

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
