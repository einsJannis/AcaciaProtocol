package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.packet.clientbound.Disconnect

object Disconnect : PacketMeta<Disconnect>(0x19, ConnectionState.PLAY, Bound.CLIENT, ::Disconnect)
