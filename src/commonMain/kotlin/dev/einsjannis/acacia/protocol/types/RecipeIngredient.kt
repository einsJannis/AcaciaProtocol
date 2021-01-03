package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class RecipeIngredient : PacketObject() {
    var count by varInt()
    var slots by slot().array(::count)
}
