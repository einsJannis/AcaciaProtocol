package dev.einsjannis.acacia.protocol

class AdvancementProgressCriteria : PacketObject() {
    var identifier by id()
    var achieved by bool()
    var dateOfAchieving by long().onlyIf(::achieved)
}
