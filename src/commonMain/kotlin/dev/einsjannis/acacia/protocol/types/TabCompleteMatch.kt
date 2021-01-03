package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class TabCompleteMatch : PacketObject() {
    var match by string()
    var hasTooltip by bool()
    var tooltip by chat().onlyIf(::hasTooltip)
}
