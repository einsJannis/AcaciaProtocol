package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*
import dev.einsjannis.acacia.protocol.types.AdvancementMap
import dev.einsjannis.acacia.protocol.types.AdvancementProgress

class Advancements : Packet() {
    var reset by bool()
    var mappingSize by varInt()
    var advancementMapping by `object`(::AdvancementMap).array(::mappingSize)
    var identifiersSize by varInt()
    var identifiers by id().array(::identifiersSize)
    var progressSize by varInt()
    var progress by `object`(::AdvancementProgress).array(::progressSize)
    companion object : PacketMeta<Advancements>(0x57, ConnectionState.PLAY, Bound.CLIENT, ::Advancements)
}
