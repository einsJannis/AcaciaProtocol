package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class UUIDDelegate : BaseDelegate<UUID>() {
    override fun read(reader: PrimitiveReader): UUID = reader.readUUID()
    override fun write(writer: PrimitiveWriter, value: UUID) = writer.writeUUID(value)
}
