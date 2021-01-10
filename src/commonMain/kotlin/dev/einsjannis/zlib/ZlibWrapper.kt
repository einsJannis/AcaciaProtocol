package dev.einsjannis.zlib

expect object ZlibWrapper {
    fun uncompress(array: ByteArray): ByteArray
    fun compress(array: ByteArray): ByteArray
}
