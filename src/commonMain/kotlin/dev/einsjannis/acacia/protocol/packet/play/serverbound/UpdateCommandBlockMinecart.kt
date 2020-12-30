package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UpdateCommandBlockMinecart : Packet() {
    var entityId by varInt()
    var command by string()
    var trackOutput by bool()

    companion object :
        PacketMeta<UpdateCommandBlockMinecart>(0x27, ConnectionState.PLAY, Bound.SERVER, ::UpdateCommandBlockMinecart)
}
