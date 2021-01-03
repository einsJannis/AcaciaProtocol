package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class StringDelegate : BaseDelegate<String>() {
    override fun read(reader: PrimitiveReader): String = reader.readString()
    override fun write(writer: PrimitiveWriter, value: String) = writer.writeString(value)
}
