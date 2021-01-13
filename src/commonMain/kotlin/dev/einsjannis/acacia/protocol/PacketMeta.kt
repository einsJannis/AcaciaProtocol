package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

/**
 * Meta for [Packet]s. Should be implemented in the companion object of a [Packet].
 */
abstract class PacketMeta<T : Packet>(
    val id: Int,
    val connectionState: ConnectionState,
    val bound: Bound,
    val constrctor: () -> T
) {

    /**
     * Reads this Packet from a [primitiveReader]. Expects to receive a [PrimitiveReader] with remaining bytes set to
     * the end of the packet.
     */
    suspend fun readPacket(primitiveReader: PrimitiveReader): T {
        val packet = constrctor()
        packet.delegates.forEach { it.readValue(primitiveReader) }
        return packet
    }

    /**
     * Writes this [packet] to the [primitiveWriter].
     */
    suspend fun writePacket(primitiveWriter: PrimitiveWriter, packet: T) {
        packet.delegates.forEach { it.writeValue(primitiveWriter) }
    }

}
