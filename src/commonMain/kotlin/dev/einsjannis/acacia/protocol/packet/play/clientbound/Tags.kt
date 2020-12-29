package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.Tag

class Tags : Packet() {
    var blockTagsSize by varInt()
    var blockTags by `object`(::Tag).array(::blockTagsSize)
    var itemTagsSize by varInt()
    var itemTags by `object`(::Tag).array(::itemTagsSize)
    var fluidTagsSize by varInt()
    var fluidTags by `object`(::Tag).array(::fluidTagsSize)
    var entityTagsSize by varInt()
    var entityTags by `object`(::Tag).array(::entityTagsSize)
}
