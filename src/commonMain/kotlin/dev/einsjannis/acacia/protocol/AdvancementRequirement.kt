package dev.einsjannis.acacia.protocol

class AdvancementRequirement : PacketObject() {
    var arrayLength by varInt()
    var array by string().array(::arrayLength)
}
