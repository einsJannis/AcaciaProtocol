package dev.einsjannis.acacia.protocol

class Equipment : PacketObject() {
    var slotE by byte()
    var item by slot()
}
