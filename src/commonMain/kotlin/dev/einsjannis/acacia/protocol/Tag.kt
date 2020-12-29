package dev.einsjannis.acacia.protocol

class Tag : PacketObject() {
    var tagName by id()
    var count by varInt()
    var entries by varInt().array(::count)
}
