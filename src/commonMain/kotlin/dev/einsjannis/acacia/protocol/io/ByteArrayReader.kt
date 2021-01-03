package dev.einsjannis.acacia.protocol.io

class ByteArrayReader(val data: ByteArray) : AbstractReader() {

    var index = 0

    override fun readByteArray(size: Int): ByteArray = data.sliceArray(index..(index + size))
        .also { index += size }

    override val remainingBytes: Int
        get() = data.size - index

}
