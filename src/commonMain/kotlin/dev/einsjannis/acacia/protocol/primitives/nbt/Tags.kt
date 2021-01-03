package dev.einsjannis.acacia.protocol.primitives.nbt

enum class NbtTypeId(val id: Byte) {
    END(0),
    BYTE(1),
    SHORT(2),
    INT(3),
    LONG(4),
    FLOAT(5),
    DOUBLE(6),
    BYTE_ARRAY(7),
    STRING(8),
    LIST(9),
    COMPOUND(10),
    INT_ARRAY(11),
    LONG_ARRAY(12),
}

sealed class NbtTag(val type: NbtTypeId)
data class ByteTag(var value: Byte) : NbtTag(NbtTypeId.BYTE)
data class ShortTag(var value: Short) : NbtTag(NbtTypeId.SHORT)
data class IntTag(var value: Int) : NbtTag(NbtTypeId.INT)
data class LongTag(var value: Long) : NbtTag(NbtTypeId.LONG)
data class FloatTag(var value: Float) : NbtTag(NbtTypeId.FLOAT)
data class DoubleTag(var value: Double) : NbtTag(NbtTypeId.DOUBLE)
data class ByteArrayTag(var value: ByteArray) : NbtTag(NbtTypeId.BYTE_ARRAY) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ByteArrayTag

        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }
}

data class StringTag(var value: String) : NbtTag(NbtTypeId.STRING)
data class ListTag(var typeId: NbtTypeId, var values: List<NbtTag>) : NbtTag(NbtTypeId.LIST)
data class CompoundTag(var map: Map<String, NbtTag>) : NbtTag(NbtTypeId.COMPOUND)
data class IntArrayTag(var value: IntArray) : NbtTag(NbtTypeId.INT_ARRAY) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as IntArrayTag

        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }
}

data class LongArrayTag(var value: LongArray) : NbtTag(NbtTypeId.LONG_ARRAY) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as LongArrayTag

        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }
}

