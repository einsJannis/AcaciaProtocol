package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class ObjectDelegate<T : PacketObject>(val packetObjectConstructor: () -> T) : BaseDelegate<T>() {
    override fun read(reader: PrimitiveReader): T = packetObjectConstructor()
        .also { it.delegates.forEach { it.readValue(reader) } }
    override fun write(writer: PrimitiveWriter, value: T) = value.delegates.forEach { it.writeValue(writer) }
    
    override val isValid: Boolean
        get() = super.isValid && _value!!.isValid
    
}
