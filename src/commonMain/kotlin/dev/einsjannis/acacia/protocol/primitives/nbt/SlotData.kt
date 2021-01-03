package dev.einsjannis.acacia.protocol.primitives.nbt

sealed class SlotData
object EmptySlot : SlotData()
data class FilledSlot(
    var itemId: Int,
    var itemCount: Byte,
    var nbt: NbtTag?,
) : SlotData()
