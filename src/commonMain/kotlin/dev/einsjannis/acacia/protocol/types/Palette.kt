package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

sealed class Palette<BS> {
    abstract fun getIdForState(state: BS): UInt
    abstract fun getStateForId(id: UInt): BS
    abstract val bitsPerBlock: Int
    abstract val network: NetworkPalette
}

abstract class GlobalPalette<BS> : Palette<BS>() {
    override val network: NetworkPalette = NetworkGlobalPalette(bitsPerBlock)
}

class IndexedPalette<BS>
private constructor(
    val globalPalette: GlobalPalette<BS>,
    val mapping: Map<UInt, UInt>,// indexed to global
    val reverseMapping: Map<UInt, UInt>, // global to indexed
) : Palette<BS>() {
    init {
        if (mapping.size != reverseMapping.size || mapping.keys.maxOrNull()
                ?.let { it.toInt() == mapping.size } == true
        )
            throw IllegalStateException("Indexed Palette not lexicographically indexed.")
    }

    override val bitsPerBlock: Int = (32 - mapping.size.countLeadingZeroBits()).coerceAtLeast(4)
    override fun getIdForState(state: BS): UInt = mapping[globalPalette.getIdForState(state)]!!

    override fun getStateForId(id: UInt): BS =
        globalPalette.getStateForId(reverseMapping[id]!!)

    override val network: NetworkIndexedPalette
        get() = NetworkIndexedPalette.ofMap(mapping)

    companion object {
        fun <BS> ofMap(globalPalette: GlobalPalette<BS>, map: Map<UInt, UInt>) =
            IndexedPalette(globalPalette, map, map.map { (k, v) -> v to k }.toMap())

        fun <BS> ofMap(globalPalette: GlobalPalette<BS>, map: Map<UInt, BS>) =
            ofMap(globalPalette, map.map { (k, v) -> k to globalPalette.getIdForState(v) }.toMap())

        fun <BS> ofBlockStates(globalPalette: GlobalPalette<BS>, list: List<BS>) =
            ofMap<BS>(globalPalette, list.withIndex().map {
                it.index.toUInt() to it.value
            }.toMap())

        fun <BS> ofBlockStates(globalPalette: GlobalPalette<BS>, vararg blocks: BS) =
            ofBlockStates(globalPalette, blocks.toList())
    }
}

sealed class NetworkPalette {
    abstract fun read(reader: PrimitiveReader)
    abstract fun write(writer: PrimitiveWriter)
    abstract fun <BS> bind(globalPalette: GlobalPalette<BS>): Palette<BS>
    abstract val bitsPerBlock: Int
}

class NetworkGlobalPalette(override val bitsPerBlock: Int) : NetworkPalette() {
    override fun <BS> bind(globalPalette: GlobalPalette<BS>): GlobalPalette<BS> = globalPalette
    override fun read(reader: PrimitiveReader) {}
    override fun write(writer: PrimitiveWriter) {}
}

class NetworkIndexedPalette private constructor(
    val mapping: MutableMap<UInt, UInt>,
) : NetworkPalette() {
    companion object {
        fun ofMap(map: Map<UInt, UInt>) = NetworkIndexedPalette(map.toMutableMap())
        fun empty() = NetworkIndexedPalette(mutableMapOf())
    }

    override fun read(reader: PrimitiveReader) {
        val length = reader.readVarInt()
        (0 until length).forEach {
            mapping[it.toUInt()] = reader.readVarInt().toUInt()
        }
    }

    override fun write(writer: PrimitiveWriter) {
        writer.writeVarInt(mapping.size)
        mapping.keys.forEach {
            writer.writeVarInt(it.toInt())
        }
    }

    override val bitsPerBlock: Int = (32 - mapping.size.countLeadingZeroBits()).coerceAtLeast(4)

    override fun <BS> bind(globalPalette: GlobalPalette<BS>): Palette<BS> =
        IndexedPalette.ofMap(globalPalette, mapping)


}
