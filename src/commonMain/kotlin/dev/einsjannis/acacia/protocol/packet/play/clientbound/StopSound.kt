package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*

class StopSound : Packet() {
    var flags by byte().mapped({ Flags(it.toInt()) }, { it.value.toByte() })
    var source by varInt().onlyIf({ flags[3] || flags[1] }, {  })
    var sound by id().onlyIf({ flags[2] || flags[3] }, {  })
    companion object : PacketMeta<StopSound>(0x52, ConnectionState.PLAY, Bound.CLIENT, ::StopSound)
}
