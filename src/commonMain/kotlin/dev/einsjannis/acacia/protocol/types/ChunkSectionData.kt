package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class ChunkSectionData : PacketObject() {
    var blockCount by short()
    var bitsPerBlock by ubyte()
    var palette by long() //TODO
    var dataArraySize by varInt()
    var dataArray by long().array(::dataArraySize)
}
