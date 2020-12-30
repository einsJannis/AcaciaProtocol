package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class SetBeaconEffect : Packet() {
    var primaryEffect by varInt()
    var secondaryEffect by varInt()

    companion object : PacketMeta<SetBeaconEffect>(0x24, ConnectionState.PLAY, Bound.SERVER, ::SetBeaconEffect)
}
