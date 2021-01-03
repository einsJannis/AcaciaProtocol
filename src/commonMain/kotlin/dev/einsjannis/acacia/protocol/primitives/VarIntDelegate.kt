package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class VarIntDelegate : BaseDelegate<Int>() {
    override fun read(reader: PrimitiveReader): Int = reader.readVarInt()
    override fun write(writer: PrimitiveWriter, value: Int) = writer.writeVarInt(value)
}
