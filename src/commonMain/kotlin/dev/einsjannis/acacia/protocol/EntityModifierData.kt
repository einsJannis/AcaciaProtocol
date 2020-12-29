package dev.einsjannis.acacia.protocol

class EntityModifierData : PacketObject() {
    var uniqueId by uuid()
    var amount by double()
    var operation by byte()
}
