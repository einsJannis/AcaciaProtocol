package dev.einsjannis.zlib

import java.io.ByteArrayInputStream
import java.util.zip.DeflaterInputStream
import java.util.zip.InflaterInputStream

actual object ZlibWrapper {
    actual fun compress(array: ByteArray): ByteArray =
        DeflaterInputStream(ByteArrayInputStream(array)).use { it.readAllBytes() }

    actual fun uncompress(array: ByteArray): ByteArray =
        InflaterInputStream(ByteArrayInputStream(array)).use { it.readAllBytes() }
}
