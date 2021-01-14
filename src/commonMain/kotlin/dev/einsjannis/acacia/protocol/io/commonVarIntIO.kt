package dev.einsjannis.acacia.protocol.io

import kotlin.experimental.and
import kotlin.experimental.or

suspend fun readVarInt(readByte: suspend () -> Byte): Int {
    var numRead = 0
    var result = 0
    var read: Byte
    do {
        read = readByte()
        val value: Int = (read and 127).toInt()
        result = result or (value shl 7 * numRead)
        numRead++
        if (numRead > 5) {
            throw RuntimeException("VarInt is too big")
        }
    } while (read and 128.toByte() != 0.toByte())
    return result
}

suspend fun writeVarInt(writeByte: suspend (Byte) -> Unit, value: Int) {
    var work = value
    do {
        var temp = (work and 0b01111111).toByte()
        work = work ushr 7
        if (work != 0) {
            temp = temp or 0b10000000.toByte()
        }
        writeByte(temp)
    } while (work != 0)
}
