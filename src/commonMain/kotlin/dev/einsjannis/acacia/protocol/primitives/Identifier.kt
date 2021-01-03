package dev.einsjannis.acacia.protocol.primitives

data class Identifier(var namespace: String, var name: String) {
    override fun toString(): String = "$namespace:$name"
}
