package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

abstract class PacketMeta<T : Packet>(val id: Int, val connectionState: ConnectionState, val bound: Bound, val constrctor: () -> T) {

    suspend fun readPacket(primitiveReader: PrimitiveReader): T {
        val packet = constrctor()
        packet.delegates.forEach { it.readValue(primitiveReader) }
        return packet
    }

    suspend fun writePacket(primitiveWriter: PrimitiveWriter, packet: T) {
        packet.delegates.forEach { it.writeValue(primitiveWriter) }
    }

}
