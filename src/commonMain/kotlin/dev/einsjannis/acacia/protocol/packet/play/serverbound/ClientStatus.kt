package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.types.StatusAction

class ClientStatus : Packet() {
    var actionId by varInt().mapped({ StatusAction.values()[it] }, { it.ordinal })
    companion object : PacketMeta<ClientStatus>(0x04, ConnectionState.PLAY, Bound.SERVER, ::ClientStatus)
}
