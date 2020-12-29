package dev.einsjannis.acacia.protocol

class MapIcon : PacketObject() {
    var type by varInt()
    var x by byte()
    var z by byte()
    var direction by byte()
    var hasDisplayName by bool()
    var displayName by chat().onlyIf(::hasDisplayName)
}
