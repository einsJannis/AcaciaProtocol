package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.exception.InvalidPacketObjectException
import dev.einsjannis.acacia.protocol.primitives.Flags
import io.ktor.utils.io.*
import kotlin.experimental.and
import kotlin.experimental.or

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

suspend fun ByteWriteChannel.writeVarInt(value: Int) {
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

fun <T : PacketObject> T.build(builder: T.() -> Unit): T {
    also(builder)
    if (!isValid) throw InvalidPacketObjectException(this)
    return this
}

fun <T : Packet> PacketMeta<T>.build(builder: T.() -> Unit): T = this.constrctor().build(builder)
