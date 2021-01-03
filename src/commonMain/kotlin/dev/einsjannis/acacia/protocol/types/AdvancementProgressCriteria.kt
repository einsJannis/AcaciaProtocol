package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class AdvancementProgressCriteria : PacketObject() {
    var identifier by id()
    var achieved by bool()
    var dateOfAchieving by long().onlyIf(::achieved)
}
