package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class DoubleDelegate : BaseDelegate<Double>() {
    override fun read(reader: PrimitiveReader): Double = reader.readDouble()
    override fun write(writer: PrimitiveWriter, value: Double) = writer.writeDouble(value)
}
