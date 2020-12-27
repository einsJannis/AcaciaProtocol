package dev.einsjannis.acacia.protocol.nbt

sealed class SlotData
object EmptySlot : SlotData()
data class FilledSlot(
    var itemId: Int,
    var itemCount: Int,
    var nbt: NbtTag?,
)
