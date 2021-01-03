package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.types.mappedAsHand

class EditBook : Packet() {
    var newBook = slot()
    var isSigning by bool()
    var hand by varInt().mappedAsHand()

    companion object : PacketMeta<EditBook>(0x0C, ConnectionState.PLAY, Bound.SERVER, ::EditBook)
}
