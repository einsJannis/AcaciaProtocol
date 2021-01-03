package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.primitives.FlagBit
import dev.einsjannis.acacia.protocol.primitives.Flags

class PlayerAbilityFlags(value: Int) : Flags(value) {
    var flying by FlagBit(1)
}
