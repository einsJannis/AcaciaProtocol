package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class Equipment : PacketObject() {
    var slotE by byte()
    var item by slot()
}
