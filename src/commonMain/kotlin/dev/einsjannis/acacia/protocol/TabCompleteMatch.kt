package dev.einsjannis.acacia.protocol

class TabCompleteMatch : PacketObject() {
    var match by string()
    var hasTooltip by bool()
    var tooltip by chat().onlyIf(::hasTooltip)
}
