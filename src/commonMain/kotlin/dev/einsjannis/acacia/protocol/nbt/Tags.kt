package dev.einsjannis.acacia.protocol.nbt

enum class NbtTypeId(val id: Int) {
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
data class ByteTag(val value: Byte) : NbtTag(NbtTypeId.BYTE)
data class ShortTag(val value: Short) : NbtTag(NbtTypeId.SHORT)
data class IntTag(val value: Int) : NbtTag(NbtTypeId.INT)
data class LongTag(val value: Long) : NbtTag(NbtTypeId.LONG)
data class FloatTag(val value: Float) : NbtTag(NbtTypeId.FLOAT)
data class DoubleTag(val value: Double) : NbtTag(NbtTypeId.DOUBLE)
data class ByteArrayTag(val value: ByteArray) : NbtTag(NbtTypeId.BYTE_ARRAY) {
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

data class StringTag(val value: String) : NbtTag(NbtTypeId.STRING)
data class ListTag(val typeId: NbtTypeId, val values: List<NbtTag>) : NbtTag(NbtTypeId.LIST)
data class CompoundTag(val map: Map<String, NbtTag>) : NbtTag(NbtTypeId.COMPOUND)
data class IntArrayTag(val value: IntArray) : NbtTag(NbtTypeId.INT_ARRAY) {
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

data class LongArrayTag(val value: LongArray) : NbtTag(NbtTypeId.LONG_ARRAY) {
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

