package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class FloatDelegate : BaseDelegate<Float>() {
    override fun read(reader: PrimitiveReader): Float = reader.readFloat()
    override fun write(writer: PrimitiveWriter, value: Float) = writer.writeFloat(value)
}
