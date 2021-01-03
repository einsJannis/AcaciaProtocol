package dev.einsjannis.acacia.protocol.primitives

data class Identifier(var namespace: String, var name: String) {
    override fun toString(): String = "$namespace:$name"
    companion object {
        fun from(value: String): Identifier {
            val array = value.split(":")
            return when (array.size) {
                1 -> Identifier("minecraft", array[0])
                2 -> Identifier(array[0], array[1])
                else -> throw IllegalArgumentException(value)
            }
        }
    }
}
