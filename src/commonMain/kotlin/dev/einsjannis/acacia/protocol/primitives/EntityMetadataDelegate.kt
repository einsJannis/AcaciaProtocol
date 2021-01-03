package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import dev.einsjannis.acacia.protocol.types.entity.EntityDataField

class EntityMetadataDelegate : BaseDelegate<List<EntityDataField>>() {
    override fun read(reader: PrimitiveReader): List<EntityDataField> = reader.readEntityMetadata()
    override fun write(writer: PrimitiveWriter, value: List<EntityDataField>) = writer.writeEntityMetadata(value)
}
