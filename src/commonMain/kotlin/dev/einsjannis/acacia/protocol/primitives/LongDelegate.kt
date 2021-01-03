package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class LongDelegate : BaseDelegate<Long>() {
    override fun read(reader: PrimitiveReader): Long = reader.readLong()
    override fun write(writer: PrimitiveWriter, value: Long) = writer.writeLong(value)
}
