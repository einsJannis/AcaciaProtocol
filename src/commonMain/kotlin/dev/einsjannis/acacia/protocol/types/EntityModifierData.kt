package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class EntityModifierData : PacketObject() {
    var uniqueId by uuid()
    var amount by double()
    var operation by byte()
}
