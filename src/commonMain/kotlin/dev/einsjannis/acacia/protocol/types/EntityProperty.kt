package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class EntityProperty : PacketObject() {
    var key by id()
    var value by double()
    var modifiersCount by varInt()
    var modifiers by `object`(::EntityModifierData).array(::modifiersCount)
}
