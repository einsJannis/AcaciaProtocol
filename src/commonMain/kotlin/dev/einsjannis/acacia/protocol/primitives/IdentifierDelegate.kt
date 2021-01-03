package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class IdentifierDelegate : BaseDelegate<Identifier>() {
    override fun read(reader: PrimitiveReader): Identifier = reader.readIdentifier()
    override fun write(writer: PrimitiveWriter, value: Identifier) = writer.writeIdentifier(value)
}
