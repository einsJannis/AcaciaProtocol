package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import kotlin.reflect.KProperty

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
