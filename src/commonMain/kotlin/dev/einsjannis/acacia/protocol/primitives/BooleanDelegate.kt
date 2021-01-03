package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class BooleanDelegate : BaseDelegate<Boolean>() {
    override fun read(reader: PrimitiveReader): Boolean = reader.readBoolean()
    override fun write(writer: PrimitiveWriter, value: Boolean) = writer.writeBoolean(value)
}
