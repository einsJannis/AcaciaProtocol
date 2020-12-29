package dev.einsjannis.acacia.protocol

import kotlin.reflect.KMutableProperty0

class PlayerInfoPlayer(action: KMutableProperty0<Int>) : PacketObject() {
    var UUID by uuid()
    var name by string().onlyIf({ action.get() == 0 }, { action.set(0) })
    var propertiesSize by varInt().onlyIf({ action.get() == 0 }, { action.set(0) })
    var properties by `object`(::PlayerInfoPlayerProperty)
        .array({ propertiesSize!! }, { propertiesSize = it })
        .onlyIf({ action.get() == 0 }, { action.set(0) })
    var gamemode by varInt().onlyIf({ action.get() == 0 || action.get() == 1 }, { action.set(1) })
    var ping by varInt().onlyIf({ action.get() == 0 || action.get() == 2 }, { action.set(2) })
    var hasDisplayName by bool().onlyIf({ action.get() == 0 || action.get() == 3 }, { action.set(3) })
    var displayName by string().onlyIf({ (action.get() == 0 || action.get() == 3) && hasDisplayName!! }, { if (it) action.set(3); hasDisplayName = it })
}
