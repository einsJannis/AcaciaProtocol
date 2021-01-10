package dev.einsjannis.zlib

import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.toCValues
import platform.zlib.Z_DATA_ERROR
import platform.zlib.Z_DEFAULT_COMPRESSION
import platform.zlib.Z_FINISH
import platform.zlib.Z_MEM_ERROR
import platform.zlib.Z_NEED_DICT
import platform.zlib.Z_NO_FLUSH
import platform.zlib.Z_OK
import platform.zlib.Z_STREAM_ERROR
import platform.zlib.deflate
import platform.zlib.deflateEnd
import platform.zlib.deflateInit
import platform.zlib.inflate
import platform.zlib.inflateEnd
import platform.zlib.inflateInit
import platform.zlib.uByteVar
import platform.zlib.z_stream

actual object ZlibWrapper {

    const val BUFFER_SIZE = 4096u

    actual fun compress(array: ByteArray): ByteArray = memScoped {
        val strm = alloc<z_stream>()
        strm.zalloc = null
        strm.zfree = null
        strm.opaque = null
        if (deflateInit(strm.ptr, Z_DEFAULT_COMPRESSION) != Z_OK) throw RuntimeException("Failed to initialize zlib")
        strm.avail_in = array.size.toUInt()
        strm.next_in = array.toUByteArray().toCValues().ptr
        val nOutArray = allocArray<uByteVar>(BUFFER_SIZE.toInt())
        var accumulator = byteArrayOf()
        do {
            strm.next_out = nOutArray
            strm.avail_out = BUFFER_SIZE
            if (deflate(strm.ptr, Z_FINISH) == Z_STREAM_ERROR) throw  RuntimeException("Zlib stream error")
            val have = BUFFER_SIZE - strm.avail_out
            val readBytes = nOutArray.readBytes(have.toInt())
            accumulator += readBytes
        } while (strm.avail_out == 0u)
        deflateEnd(strm.ptr)
        accumulator
    }

    actual fun uncompress(array: ByteArray): ByteArray = memScoped {
        val strm = alloc<z_stream>()
        strm.zalloc = null
        strm.zfree = null
        strm.opaque = null
        if (inflateInit(strm.ptr) != Z_OK) throw RuntimeException("Failed to initialize zlib")
        strm.avail_in = array.size.toUInt()
        strm.next_in = array.toUByteArray().toCValues().ptr
        val nOutArray = allocArray<uByteVar>(BUFFER_SIZE.toInt())
        var accumulator = byteArrayOf()
        do {
            strm.avail_out = BUFFER_SIZE
            strm.next_out = nOutArray
            when (inflate(strm.ptr, Z_NO_FLUSH)) {
                Z_NEED_DICT -> throw RuntimeException("Zlib needs dict")
                Z_DATA_ERROR -> throw RuntimeException("Invalid zlib data")
                Z_MEM_ERROR -> {
                    inflateEnd(strm.ptr)
                    throw RuntimeException("Running out of memory while attempting to decompress zlib data")
                }
                else -> {
                }
            }
            val have = BUFFER_SIZE - strm.avail_out
            accumulator += nOutArray.readBytes(have.toInt())
        } while (strm.avail_out == 0u)
        inflateEnd(strm.ptr)
        accumulator
    }
}
