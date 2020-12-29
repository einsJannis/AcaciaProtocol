package dev.einsjannis.acacia.protocol

class PlayerAbilityFlags(value: Int) : Flags(value) {
    var flying by FlagBit(1)
}
