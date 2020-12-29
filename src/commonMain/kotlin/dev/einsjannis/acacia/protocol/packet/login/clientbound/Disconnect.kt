package dev.einsjannis.acacia.protocol.packet.login.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.packet.clientbound.Disconnect

object Disconnect : PacketMeta<Disconnect>(0x00, ConnectionState.LOGIN, Bound.CLIENT, ::Disconnect)
