package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.BaseDelegate

enum class Hand {
    MAINHAND,
    OFFHAND,
}

fun BaseDelegate<Int>.mappedAsHand() = mapped({ Hand.values()[it] }, { it.ordinal })
