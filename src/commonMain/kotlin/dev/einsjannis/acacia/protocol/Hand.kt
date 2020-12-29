package dev.einsjannis.acacia.protocol

enum class Hand {
    MAINHAND,
    OFFHAND,
}

fun BaseDelegate<Int>.mappedAsHand() = mapped({ Hand.values()[it] }, { it.ordinal })
