package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class UShortDelegate : BaseDelegate<UShort>() {
    override fun read(reader: PrimitiveReader): UShort = reader.readUnsignedShort()
    override fun write(writer: PrimitiveWriter, value: UShort) = writer.writeUnsignedShort(value)
}
