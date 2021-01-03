package dev.einsjannis.acacia.protocol.io

import dev.einsjannis.acacia.protocol.primitives.Identifier
import dev.einsjannis.acacia.protocol.primitives.UUID
import dev.einsjannis.acacia.protocol.primitives.chat.ChatComponent
import dev.einsjannis.acacia.protocol.primitives.nbt.NbtTag
import dev.einsjannis.acacia.protocol.primitives.nbt.SlotData
import dev.einsjannis.acacia.protocol.types.Position
import dev.einsjannis.acacia.protocol.types.entity.EntityDataField

abstract class AbstractReader : PrimitiveReader {

    override fun readBoolean(): Boolean {
        TODO("Not yet implemented")
    }

    override fun readByte(): Byte = readByteArray(1).first()

    override fun readUnsignedByte(): UByte {
        TODO("Not yet implemented")
    }

    override fun readShort(): Short {
        TODO("Not yet implemented")
    }

    override fun readUnsignedShort(): UShort {
        TODO("Not yet implemented")
    }

    override fun readInt(): Int {
        TODO("Not yet implemented")
    }

    override fun readLong(): Long {
        TODO("Not yet implemented")
    }

    override fun readFloat(): Float {
        TODO("Not yet implemented")
    }

    override fun readDouble(): Double {
        TODO("Not yet implemented")
    }

    override fun readString(): String {
        TODO("Not yet implemented")
    }

    override fun readChat(): ChatComponent {
        TODO("Not yet implemented")
    }

    override fun readIdentifier(): Identifier {
        TODO("Not yet implemented")
    }

    override fun readVarInt(): Int {
        TODO("Not yet implemented")
    }

    override fun readVarLong(): Long {
        TODO("Not yet implemented")
    }

    override fun readEntityMetadata(): List<EntityDataField> {
        TODO("Not yet implemented")
    }

    override fun readSlot(): SlotData {
        TODO("Not yet implemented")
    }

    override fun readNBTTag(): NbtTag {
        TODO("Not yet implemented")
    }

    override fun readPosition(): Position {
        TODO("Not yet implemented")
    }

    override fun readUUID(): UUID {
        TODO("Not yet implemented")
    }
}
