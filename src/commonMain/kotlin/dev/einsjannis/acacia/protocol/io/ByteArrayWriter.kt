package dev.einsjannis.acacia.protocol.io

class ByteArrayWriter : AbstractWriter() {

    var result: ByteArray = byteArrayOf()
        private set

    override fun writeByteArray(value: ByteArray) {
        result += value
    }

}
