package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import dev.einsjannis.acacia.protocol.types.Position

class PositionDelegate : BaseDelegate<Position>() {
    override fun read(reader: PrimitiveReader): Position = reader.readPosition()
    override fun write(writer: PrimitiveWriter, value: Position) = writer.writePosition(value)
}
