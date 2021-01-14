package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.exception.InvalidPacketObjectException
import dev.einsjannis.acacia.protocol.primitives.Flags

inline fun <reified T : Enum<T>> BaseDelegate<Int>.enumOrdinalMapping() =
    mapped({ enumValues<T>()[it] }, { it.ordinal })

fun <T : Flags> BaseDelegate<Int>.bitFlag(con: (Int) -> T) = mapped<T>(con, { it.value })


fun <T : PacketObject> T.build(builder: T.() -> Unit): T {
    also(builder)
    if (!isValid) throw InvalidPacketObjectException(this)
    return this
}

fun <T : Packet> PacketMeta<T>.build(builder: T.() -> Unit): T = this.constrctor().build(builder)
