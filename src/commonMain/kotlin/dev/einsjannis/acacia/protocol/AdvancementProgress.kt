package dev.einsjannis.acacia.protocol

class AdvancementProgress : Packet() {
    var size by varInt()
    var criteria by `object`(::AdvancementProgressCriteria).array(::size)
}
