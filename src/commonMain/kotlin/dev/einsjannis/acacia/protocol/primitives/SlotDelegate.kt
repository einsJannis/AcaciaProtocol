package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import dev.einsjannis.acacia.protocol.primitives.nbt.SlotData

class SlotDelegate : BaseDelegate<SlotData>() {
    override fun read(reader: PrimitiveReader): SlotData = reader.readSlot()
    override fun write(writer: PrimitiveWriter, value: SlotData) = writer.writeSlot(value)
}
