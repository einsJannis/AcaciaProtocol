package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*

class AcknowledgePlayerDigging : Packet() {
    var location by position()
    var block by varInt()
    var status by varInt().mapped({ Process.values()[it] }, { it.ordinal })
    var successful by bool()
    companion object : PacketMeta<AcknowledgePlayerDigging>(0x07, ConnectionState.PLAY, Bound.CLIENT, ::AcknowledgePlayerDigging)
}
