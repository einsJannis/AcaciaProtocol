package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class AdvancementRequirement : PacketObject() {
    var arrayLength by varInt()
    var array by string().array(::arrayLength)
}
