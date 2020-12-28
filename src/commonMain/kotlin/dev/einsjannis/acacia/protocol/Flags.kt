package dev.einsjannis.acacia.protocol

class Flags(var value: Int) {

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
