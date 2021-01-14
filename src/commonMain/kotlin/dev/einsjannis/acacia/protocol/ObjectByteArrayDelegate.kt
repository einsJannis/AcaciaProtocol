package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.exception.NotEnoughBytesLeftException
import dev.einsjannis.acacia.protocol.io.ByteArrayReader
import dev.einsjannis.acacia.protocol.io.ByteArrayWriter
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class ObjectByteArrayDelegate<T>(val elementDelegate: BaseDelegate<T>) : BaseDelegate<List<T>>() {
    
    override fun write(writer: PrimitiveWriter, value: List<T>) {
        val data = ByteArrayWriter(writer.scope, value.size)
        value.forEach { elementDelegate.write(data, it) }
        writer.writeVarInt(value.size)
        writer.writeByteArray(data.result)
    }
    
    override fun read(reader: PrimitiveReader): List<T> {
        val size = reader.readVarInt()
        val data = ByteArrayReader(reader.readByteArray(size), reader.scope)
        return buildList {
            while (data.remainingBytes != 0) {
                try {
                    elementDelegate.read(data)
                } catch (exception: NotEnoughBytesLeftException) {
                }
            }
        }
    }
    
}
