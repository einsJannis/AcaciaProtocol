package dev.einsjannis.acacia.protocol.types

import dev.einsjannis.acacia.protocol.primitives.Flags
import dev.einsjannis.acacia.protocol.PacketObject

class AdvancementMap : PacketObject() {
    var key by id()
    var hasParent by bool()
    var parentId by id().onlyIf(::hasParent)
    var hasDisplay by bool()
    var title by chat().onlyIf(::hasDisplay)
    var description by chat().onlyIf(::hasDisplay)
    var icon by slot().onlyIf(::hasDisplay)
    var frameType by varInt().onlyIf(::hasDisplay)
    var flags by int().mapped({ Flags(it) }, { it.value }).onlyIf(::hasDisplay)
    var backgroundTexture by id().onlyIf({ hasDisplay && flags!![0x01] }, {  })
    var x by float()
    var y by float()
    var criteriaCount by varInt()
    var criteria by id().array(::criteriaCount)
    var requirementsCount by varInt()
    var requirements by `object`(::AdvancementRequirement).array(::requirementsCount)
}
