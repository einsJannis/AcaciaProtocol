package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.PacketObject
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter

class PaletteDelegate() : BaseDelegate<NetworkPalette>() {
    override fun write(writer: PrimitiveWriter, value: NetworkPalette) {
        writer.writeUnsignedByte(value.bitsPerBlock.toUByte())
        value.write(writer)
    }

    override fun read(reader: PrimitiveReader): NetworkPalette {
        val bitLength = reader.readUnsignedByte().toInt()
        return if (bitLength > 8) NetworkGlobalPalette(bitLength)
        else NetworkIndexedPalette.empty().also { it.read(reader) }
    }

}

class ChunkSectionData : PacketObject() {
    var blockCount by short()
    var palette by delegate(PaletteDelegate())
    var dataArraySize by varInt()
    var dataArray by long().array(::dataArraySize)
        .mapped(parseCompactArray(palette.bitsPerBlock), writeCompactArray(palette.bitsPerBlock))
}

fun writeCompactArray(bitsPerBlock: Int) = fun(ints: List<UInt>): List<Long> {
    val blocksPerLong = 64 / bitsPerBlock
    return ints.chunked(blocksPerLong).map {
        writeSingleLong(bitsPerBlock, it)
    }
}

fun writeSingleLong(bitsPerBlock: Int, v: List<UInt>): Long {
    var r = 0L
    v.forEachIndexed { index, uInt ->
        r = (uInt.toLong() shl (bitsPerBlock * index)) or r
    }
    return r
}

fun parseCompactArray(bitsPerBlock: Int) = fun(longs: List<Long>): List<UInt> {
    val mask = (1 shl bitsPerBlock).dec()
    return longs.flatMap {
        parseSingleLong(bitsPerBlock, mask, it)
    }
}

fun parseSingleLong(bitsPerBlock: Int, mask: Int, v: Long): Sequence<UInt> = sequence {
    (0 until (64 / bitsPerBlock)).forEach {
        yield(((v shr (64 - it * bitsPerBlock)).toInt() and mask).toUInt())
    }
}
