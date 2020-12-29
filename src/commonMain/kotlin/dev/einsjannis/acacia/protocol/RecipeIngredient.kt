package dev.einsjannis.acacia.protocol

class RecipeIngredient : PacketObject() {
    var count by varInt()
    var slots by slot().array(::count)
}
