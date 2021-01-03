package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class ByteDelegate : BaseDelegate<Byte>() {
    override fun read(reader: PrimitiveReader): Byte = reader.readByte()
    override fun write(writer: PrimitiveWriter, value: Byte) = writer.writeByte(value)
}
