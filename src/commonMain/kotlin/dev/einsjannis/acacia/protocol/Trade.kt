package dev.einsjannis.acacia.protocol

class Trade : PacketObject() {
    var inputItemA by slot()
    var outputItem by slot()
    var hasSecondItem by bool()
    var inputItemB by slot().onlyIf(::hasSecondItem)
    var tradeDisabled by bool()
    var tradeUses by int()
    var maxTradeUses by int()
    var xp by int()
    var specialPrice by int()
    var priceMultiplier by float()
    var demand by int()
}
