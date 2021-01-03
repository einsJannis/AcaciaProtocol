package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.primitives.FlagBit
import dev.einsjannis.acacia.protocol.primitives.Flags

class SkinPart(value: Int) : Flags(value) {
    var cape by FlagBit(0)
    var jacket by FlagBit(1)
    var leftSleeve by FlagBit(2)
    var rightSleeve by FlagBit(3)
    var leftPants by FlagBit(4)
    var rightPants by FlagBit(5)
    var hat by FlagBit(6)
}
