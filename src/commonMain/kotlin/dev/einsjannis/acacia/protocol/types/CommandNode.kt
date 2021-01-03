package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.primitives.Flags
import dev.einsjannis.acacia.protocol.primitives.Identifier
import dev.einsjannis.acacia.protocol.PacketObject

class CommandNode : PacketObject() {
    var flags by byte().mapped({ Flags(it.toInt()) }, { it.value.toByte() })
    var childrenCount by varInt()
    var children by varInt().array(::childrenCount)
    var redirectNode by varInt().onlyIf({ flags[8] }, { flags[8] = it })
    var name by string().onlyIf({ flags[1] || flags[2] }, { flags[1] = it })
    var parser by id().onlyIf({ flags[2] }, { flags[2] = it })
    var propertyFlags by byte().mapped({ Flags(it.toInt()) }, { it.value.toByte() }).onlyIf(
        { parser == Identifier("brigadier", "double") || parser == Identifier("brigadier", "float") || parser == Identifier("brigadier", "integer") || parser == Identifier("minecraft", "entity") || parser == Identifier("minecraft", "score_holder") },
        { }
    )
    var propertyMinD by double().onlyIf(
        { parser == Identifier("brigadier", "double") && propertyFlags != null && propertyFlags!![1] },
        { if (it) {
            parser = Identifier("brigadier", "double")
            if (propertyFlags != null) propertyFlags!![1] = true else propertyFlags = Flags(0b10)
        } }
    )
    var propertyMaxD by double().onlyIf(
        { parser == Identifier("brigadier", "double") && propertyFlags != null && propertyFlags!![2] },
        { if (it) {
            parser = Identifier("brigadier", "double")
            if (propertyFlags != null) propertyFlags!![2] = true else propertyFlags = Flags(0b100)
        } }
    )
    var propertyMinF by float().onlyIf(
        { parser == Identifier("brigadier", "float") && propertyFlags != null && propertyFlags!![1] },
        { if (it) {
            parser = Identifier("brigadier", "float")
            if (propertyFlags != null) propertyFlags!![1] = true else propertyFlags = Flags(0b10)
        } }
    )
    var propertyMaxF by float().onlyIf(
        { parser == Identifier("brigadier", "float") && propertyFlags != null && propertyFlags!![2] },
        { if (it) {
            parser = Identifier("brigadier", "float")
            if (propertyFlags != null) propertyFlags!![2] = true else propertyFlags = Flags(0b100)
        } }
    )
    var propertyMinI by int().onlyIf(
        { parser == Identifier("brigadier", "integer") && propertyFlags != null && propertyFlags!![1] },
        { if (it) {
            parser = Identifier("brigadier", "integer")
            if (propertyFlags != null) propertyFlags!![1] = true else propertyFlags = Flags(0b10)
        } }
    )
    var propertyMaxI by int().onlyIf(
        { parser == Identifier("brigadier", "integer") && propertyFlags != null && propertyFlags!![2] },
        { if (it) {
            parser = Identifier("brigadier", "integer")
            if (propertyFlags != null) propertyFlags!![2] = true else propertyFlags = Flags(0b100)
        } }
    )
    var propertyStringType by varInt().mapped(
        { CommandStringType.values()[it] },
        { it.ordinal }
    ).onlyIf(
        { parser == Identifier("brigadier", "string") },
        { if (it) parser = Identifier("brigadier", "string") }
    )
    var propertyDecimals by bool().onlyIf(
        { parser == Identifier("minecraft", "range") },
        { if (it) parser = Identifier("minecraft", "range") }
    )
    var suggestionsType by id().onlyIf({ flags[0x10] }, { flags[0x10] = it })
}
