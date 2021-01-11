package dev.einsjannis.acacia.protocol.exception

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.io.net.Client

class UnnecessaryCompressionException(val threshold: Int, val dataLength: Int, val bound: Bound) : RuntimeException() {
    
    override val message: String =
        "A compressed packet was sent to ${bound.name.toLowerCase()} which was unnessesaraly compressed. Packets should be compressed when over the size of $threshold but the compressed packet was of the size $dataLength."

}

class UnexpectedDecompressedLengthException(val expectedLength: Int, val actualLength: Int, val data: ByteArray, val decompressedData: ByteArray): RuntimeException() {
    
    override val message: String =
        "A compressed packet was prefixed with the data length $expectedLength but the actual decompressed length was $actualLength."
    
}

class NoConnectionException(val client: Client) : RuntimeException() {
    
    override val message: String =
        "The client $client has no connection to the server"
    
}
