package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.Packet

class AdvancementProgress : Packet() {
    var size by varInt()
    var criteria by `object`(::AdvancementProgressCriteria).array(::size)
}
