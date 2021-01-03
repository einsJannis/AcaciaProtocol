package dev.einsjannis.acacia.protocol.primitives

data class UUID(val upper: Long, val lower: Long) {

    override fun toString(): String {
        val upper = upper.toString(16).padStart(16, '0')
        val lower = lower.toString(16).padStart(16, '0')
        val first = upper.substring(0 until 8)
        val second = upper.substring(8 until 12)
        val third = upper.substring(12 until 16)
        val fourth = lower.substring(0 until 4)
        val fifth = lower.substring(4 until 16)
        return "$first-$second-$third-$fourth-$fifth"
    }

}
