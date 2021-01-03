package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class ShortDelegate : BaseDelegate<Short>() {
    override fun read(reader: PrimitiveReader): Short = reader.readShort()
    override fun write(writer: PrimitiveWriter, value: Short) = writer.writeShort(value)
}
