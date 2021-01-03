package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class AngleDelegate : BaseDelegate<UByte>() {
    override fun read(reader: PrimitiveReader): UByte = reader.readUnsignedByte()
    override fun write(writer: PrimitiveWriter, value: UByte) = writer.writeUnsignedByte(value)
}
