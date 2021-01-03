package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import dev.einsjannis.acacia.protocol.primitives.nbt.NbtTag

class NbtTagDelegate : BaseDelegate<NbtTag>() {
    override fun read(reader: PrimitiveReader): NbtTag = reader.readNBTTag()
    override fun write(writer: PrimitiveWriter, value: NbtTag) = writer.writeNBTTag(value)
}
