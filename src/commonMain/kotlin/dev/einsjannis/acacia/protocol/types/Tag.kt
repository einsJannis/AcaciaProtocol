package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.PacketObject

class Tag : PacketObject() {
    var tagName by id()
    var count by varInt()
    var entries by varInt().array(::count)
}
