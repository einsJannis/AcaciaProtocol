package dev.einsjannis.acacia.protocol.io

import dev.einsjannis.acacia.protocol.exception.NotEnoughBytesLeftException

class ByteArrayReader(val data: ByteArray) : AbstractReader() {

    var index = 0

    override fun readByteArray(size: Int): ByteArray {
        val end = index + size
        if (end >= data.size) throw NotEnoughBytesLeftException(size, data.size - index)
        return data.sliceArray(index..end).also { index = end }
    }

    override val remainingBytes: Int
        get() = data.size - index

}
