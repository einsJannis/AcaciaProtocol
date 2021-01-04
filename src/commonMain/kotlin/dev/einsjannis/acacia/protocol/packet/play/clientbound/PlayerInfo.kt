package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.types.PlayerInfoPlayer

class PlayerInfo : Packet() {
    var action by varInt()
    var playersSize by varInt()
    var players by `object` { PlayerInfoPlayer(::action) }.array(::playersSize)
    companion object : PacketMeta<PlayerInfo>(0x32, ConnectionState.PLAY, Bound.CLIENT, ::PlayerInfo)
}
