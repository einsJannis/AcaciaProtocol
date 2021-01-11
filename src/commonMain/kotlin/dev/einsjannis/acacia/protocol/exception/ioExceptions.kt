package dev.einsjannis.acacia.protocol.exception

class NotEnoughBytesLeftException(val amountToRead: Int, val bytesLeft: Int) : RuntimeException() {
    
    override val message: String =
        "Tried to read $amountToRead bytes but only $bytesLeft were left."
    
}
