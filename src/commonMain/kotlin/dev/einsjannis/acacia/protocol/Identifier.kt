package dev.einsjannis.acacia.protocol

data class Identifier(var namespace: String, var name: String) {
    override fun toString(): String = "$namespace:$name"
}
