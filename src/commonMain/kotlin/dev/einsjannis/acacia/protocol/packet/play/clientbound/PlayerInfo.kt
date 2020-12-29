package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PlayerInfoPlayer

class PlayerInfo : Packet() {
    var action by varInt()
    var playersSize by varInt()
    var players by `object` { PlayerInfoPlayer(::action) }.array(::playersSize)
}
