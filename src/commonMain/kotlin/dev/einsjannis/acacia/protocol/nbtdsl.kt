package dev.einsjannis.acacia.protocol

import dev.einsjannis.acacia.protocol.primitives.nbt.ByteArrayTag
import dev.einsjannis.acacia.protocol.primitives.nbt.ByteTag
import dev.einsjannis.acacia.protocol.primitives.nbt.CompoundTag
import dev.einsjannis.acacia.protocol.primitives.nbt.DoubleTag
import dev.einsjannis.acacia.protocol.primitives.nbt.ENDTag
import dev.einsjannis.acacia.protocol.primitives.nbt.FloatTag
import dev.einsjannis.acacia.protocol.primitives.nbt.IntArrayTag
import dev.einsjannis.acacia.protocol.primitives.nbt.IntTag
import dev.einsjannis.acacia.protocol.primitives.nbt.ListTag
import dev.einsjannis.acacia.protocol.primitives.nbt.LongArrayTag
import dev.einsjannis.acacia.protocol.primitives.nbt.LongTag
import dev.einsjannis.acacia.protocol.primitives.nbt.NbtTag
import dev.einsjannis.acacia.protocol.primitives.nbt.NbtTypeId
import dev.einsjannis.acacia.protocol.primitives.nbt.ShortTag
import dev.einsjannis.acacia.protocol.primitives.nbt.StringTag

@DslMarker
annotation class NBTDsl

@NBTDsl
interface DSLNbtBase<T : NbtTag> {
    fun build(): T
    fun <V : NbtTag> lift(x: V): DSLNbtBase<V> = DSLNbtPrimitve(x)
}

@NBTDsl
class DSLNbtPrimitve<T : NbtTag>(val value: T) : DSLNbtBase<T> {
    override fun build(): T = value
}

@NBTDsl
class DSLNbtList<V : NbtTag>(val typeId: NbtTypeId) : DSLNbtBase<ListTag<V>> {
    val data = mutableListOf<DSLNbtBase<V>>()
    override fun build(): ListTag<V> = ListTag(typeId, data.map { it.build() })
    fun put(value: DSLNbtBase<V>) {
        data.add(value)
    }

    fun add(value: DSLNbtBase<V>) {
        data.add(value)
    }

    fun add(value: V) {
        data.add(lift(value))
    }

    operator fun DSLNbtBase<V>.unaryPlus() {
        this@DSLNbtList.data.add(this)
    }

    operator fun V.unaryPlus() {
        this@DSLNbtList.add(this)
    }
}

fun DSLNbtList<ByteTag>.add(value: Byte) = add(ByteTag(value))
fun DSLNbtList<ShortTag>.add(value: Short) = add(ShortTag(value))
fun DSLNbtList<IntTag>.add(value: Int) = add(IntTag(value))
fun DSLNbtList<LongTag>.add(value: Long) = add(LongTag(value))
fun DSLNbtList<FloatTag>.add(value: Float) = add(FloatTag(value))
fun DSLNbtList<DoubleTag>.add(value: Double) = add(DoubleTag(value))
fun DSLNbtList<ByteArrayTag>.add(value: ByteArray) = add(ByteArrayTag(value))
fun DSLNbtList<StringTag>.add(value: String) = add(StringTag(value))
fun DSLNbtList<CompoundTag>.add(init: DSLNbtCompound.() -> Unit) = add(DSLNbtCompound().also(init))
fun DSLNbtList<IntArrayTag>.add(value: IntArray) = add(IntArrayTag(value))
fun DSLNbtList<LongArrayTag>.add(value: LongArray) = add(LongArrayTag(value))

@NBTDsl
class DSLNbtCompound : DSLNbtBase<CompoundTag> {
    val data = mutableMapOf<String, DSLNbtBase<*>>()
    fun put(name: String, value: DSLNbtBase<*>) {
        data[name] = value
    }

    fun put(name: String, value: NbtTag) {
        data[name] = lift(value)
    }

    fun byte(name: String, value: Byte) = put(name, ByteTag(value))
    fun short(name: String, value: Short) = put(name, ShortTag(value))
    fun int(name: String, value: Int) = put(name, IntTag(value))
    fun long(name: String, value: Long) = put(name, LongTag(value))
    fun float(name: String, value: Float) = put(name, FloatTag(value))
    fun double(name: String, value: Double) = put(name, DoubleTag(value))
    fun byteArray(name: String, value: ByteArray) = put(name, ByteArrayTag(value))
    fun string(name: String, value: String) = put(name, StringTag(value))
    fun list(name: String, value: DSLNbtList<*>) = put(name, value)
    fun list(name: String, value: ListTag<*>) = put(name, value)
    fun <V : NbtTag> list(name: String, kind: NbtTypeId, init: DSLNbtList<V>.() -> Unit) =
        put(name, DSLNbtList<V>(kind).also(init))

    fun compound(name: String, value: DSLNbtCompound) = put(name, value)
    fun compound(name: String, value: CompoundTag) = put(name, value)
    fun compound(name: String, init: DSLNbtCompound.() -> Unit) =
        compound(name, DSLNbtCompound().also(init))

    fun intArray(name: String, value: IntArray) = put(name, IntArrayTag(value))
    fun longArray(name: String, value: LongArray) = put(name, LongArrayTag(value))
    fun putExplicitEnd(name: String) = put(name, ENDTag)

    override fun build(): CompoundTag = CompoundTag(data.entries.map { (key, value) ->
        key to value.build()
    }.toMap())
}

@NBTDsl
fun nbtCompoundTag(init: DSLNbtCompound.() -> Unit): CompoundTag = DSLNbtCompound().also(init).build()

@NBTDsl
fun <V : NbtTag> nbtList(kind: NbtTypeId, init: DSLNbtList<V>.() -> Unit) = DSLNbtList<V>(kind).also(init).build()

