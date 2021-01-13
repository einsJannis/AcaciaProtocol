package dev.einsjannis

import kotlin.experimental.and
import kotlin.experimental.xor
import kotlin.random.Random

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

    companion object {

        fun random(version: Int = 4): UUID = when (version) {
            1 -> TODO()
            2 -> TODO()
            3 -> TODO()
            4 -> performantRandomV4()
            else -> throw IllegalArgumentException("Argument version of function random has to be 1, 2, 3 or 4")
        }

        fun randomV4(): UUID = from(
            timestamp = Random.nextLong(),
            version = 0b0100,
            reserved = 0b01,
            clockSequence = Random.nextInt().toShort(),
            node = Random.nextLong()
        )

        fun performantRandomV4(): UUID {
            var upper = Random.nextLong()
            var lower = Random.nextLong()
            upper = upper and 0b0000 or 0b0100
            lower = lower
                .and(-0b11111_00_11111111_11111111_11111111_11111111_11111111_11111111_11111111)
                .or(0b000000_01_00000000_00000000_00000000_00000000_00000000_00000000_00000000)
            return UUID(upper, lower)
        }

        fun from(timestamp: Long, version: Byte, reserved: Byte, clockSequence: Short, node: Long): UUID {
            var upper = 0L
            var lower = 0L
            upper = upper and (timestamp shl 4) and (version and 0b1111).toLong()
            val upperClockSequence = clockSequence.toLong() ushr 6 and 0b11111100
            val lowerClockSequence = clockSequence.toLong()        and 0b11111111
            lower = lower
                .and(upperClockSequence shl 56)
                .and(reserved.toLong() and 0b11 shl 56)
                .and(lowerClockSequence shl 48)
                .and(node and 0b00000000_00000000_11111111_11111111_11111111_11111111_11111111_11111111)
            return UUID(upper, lower)
        }

    }

}
