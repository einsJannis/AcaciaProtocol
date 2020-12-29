package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UpdateScore : Packet() {
    var entityName by string()
    var action by byte()
    var objectiveName by string()
    var value by varInt().onlyIf({ action != 1.toByte() }, { if (!it) action = 1 })
    companion object : PacketMeta<UpdateScore>(0x4D, ConnectionState.PLAY, Bound.CLIENT, ::UpdateScore)
}
