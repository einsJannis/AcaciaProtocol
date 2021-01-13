package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.types.ChunkSectionData

class ChunkData : Packet() {
    var x by int()
    var z by int()
    var fullChunk by bool()
    var primaryBitMask by varInt()
    var heightmaps by nbtTag()
    var biomesLength by varInt().onlyIf({ !fullChunk }, { fullChunk = !it })
    var biomes by varInt().array({ biomesLength!! }, { biomesLength = it }).onlyIf({ !fullChunk }, { fullChunk = !it })
    var data by `object`(::ChunkSectionData).byteArray()
    var numberOfBlockEntities by varInt()
    var blockEntities by nbtTag().array(::numberOfBlockEntities)
    companion object : PacketMeta<ChunkData>(0x20, ConnectionState.PLAY, Bound.CLIENT, ::ChunkData)
}
