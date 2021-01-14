package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.ByteArrayWriter
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class ObjectByteArrayDelegate<T>(val elementDelegate: BaseDelegate<T>) : BaseDelegate<List<T>>() {
    
    override fun write(writer: PrimitiveWriter, value: List<T>) {
        val data = ByteArrayWriter(value.size)
        value.forEach { elementDelegate.write(data, it) }
        writer.writeVarInt(value.size)
        writer.writeByteArray(data.result)
    }
    
    override fun read(reader: PrimitiveReader): List<T> {
        val size = reader.readVarInt()
        val remainingBytesStart = reader.remainingBytes
        return buildList {
            while (reader.remainingBytes - remainingBytesStart < size) {
                elementDelegate.read(reader)
            }
        }
    }
}
