package dev.einsjannis.acacia.protocol.types.statistic

import dev.einsjannis.acacia.protocol.PacketObject

class Statistic : PacketObject() {
    var categoryId by varInt().mapped({ CategoryID.values()[it] }, { it.ordinal })
    var statisticId by varInt().mapped({ StatisticID.values()[it] }, { it.ordinal })
    var value by varInt()
}
