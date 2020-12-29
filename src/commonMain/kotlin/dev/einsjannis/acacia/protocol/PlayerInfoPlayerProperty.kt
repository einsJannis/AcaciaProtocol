package dev.einsjannis.acacia.protocol

class PlayerInfoPlayerProperty : PacketObject() {
    var name by string()
    var value by string()
    var isSigned by bool()
    var signature by string()
}
