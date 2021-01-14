package dev.einsjannis.acacia.protocol.io

import kotlinx.coroutines.CoroutineScope
import kotlin.math.max

class ByteArrayWriter(scope: CoroutineScope, capacity: Int = 0) : AbstractWriter(scope) {

    var _result: ByteArray = ByteArray(capacity)
    val result get() = _result.sliceArray(0 until size)
    var size = 0
        private set

    private inline fun ensureCapacity(minCap: Int) {
        if (minCap + size > _result.size) {
            _result = _result.copyOf(max(_result.size * 2 + 1, minCap + size))
        }
    }

    override fun writeByteArray(value: ByteArray) {
        ensureCapacity(value.size)
        value.copyInto(_result, size, 0)
        size += value.size
    }

    fun writeByteArray(value: ByteArray, fromIndex: Int, toIndex: Int) {
        ensureCapacity(toIndex - fromIndex)
        value.copyInto(_result, size, fromIndex, toIndex)
        size += value.size
    }

}
