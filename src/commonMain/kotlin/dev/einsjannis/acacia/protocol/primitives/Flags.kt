package dev.einsjannis.acacia.protocol.primitives

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class Flags(var value: Int) {

    operator fun get(index: Int): Boolean = (value ushr index) and 1 == 1

    operator fun set(index: Int, value: Boolean) {
        if (value)
            this.value = this.value or (1 shl (Int.SIZE_BITS - 1 - index))
        else
            this.value = this.value and (0 shl (Int.SIZE_BITS - 1 - index))
    }

    fun toggle(index: Int) {
        this.value = this.value xor (1 shl (Int.SIZE_BITS - 1 - index))
    }

}

class FlagBit(val index: Int) : ReadWriteProperty<Flags, Boolean> {

    override fun setValue(thisRef: Flags, property: KProperty<*>, value: Boolean) {
        thisRef[index] = value
    }

    override fun getValue(thisRef: Flags, property: KProperty<*>): Boolean =
        thisRef[index]

}
