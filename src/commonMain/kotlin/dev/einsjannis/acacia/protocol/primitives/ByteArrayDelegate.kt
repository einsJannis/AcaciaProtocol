package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.PacketObject
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import kotlin.reflect.KProperty

class ByteArrayDelegate(val getSize: (remainingBytes: Int) -> Int, val setSize: (Int) -> Unit) : BaseDelegate<ByteArray>() {
    override fun setValue(thisRef: PacketObject, property: KProperty<*>, value: ByteArray) {
        super.setValue(thisRef, property, value)
        setSize(value.size)
    }
    override fun read(reader: PrimitiveReader): ByteArray = reader.readByteArray(getSize(reader.remainingBytes))
    override fun write(writer: PrimitiveWriter, value: ByteArray) = writer.writeByteArray(value)
}
