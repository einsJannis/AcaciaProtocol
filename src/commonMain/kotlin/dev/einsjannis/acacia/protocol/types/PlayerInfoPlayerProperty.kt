package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class PlayerInfoPlayerProperty : PacketObject() {
    var name by string()
    var value by string()
    var isSigned by bool()
    var signature by string()
}
