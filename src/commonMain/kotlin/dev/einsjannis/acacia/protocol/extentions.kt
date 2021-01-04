package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.primitives.Flags
import io.ktor.utils.io.*
import kotlin.experimental.and

inline fun <reified T : Enum<T>> BaseDelegate<Int>.enumOrdinalMapping() =
    mapped({ enumValues<T>()[it] }, { it.ordinal })

fun <T : Flags> BaseDelegate<Int>.bitFlag(con: (Int) -> T) = mapped<T>(con, { it.value })

suspend fun ByteReadChannel.readVarInt(): Int {
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
