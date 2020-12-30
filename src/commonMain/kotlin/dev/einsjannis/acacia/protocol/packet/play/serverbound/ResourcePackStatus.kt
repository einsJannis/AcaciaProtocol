package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.enumOrdinalMapping

enum class ResourcePackStatusEnum {
    SUCCESSFULLY_LOADED,
    DECLINED,
    FAILED_DOWNLOAD,
    ACCEPTED,
}

class ResourcePackStatus : Packet() {
    var result by varInt().enumOrdinalMapping<ResourcePackStatusEnum>()

    companion object : PacketMeta<ResourcePackStatus>(0x21, ConnectionState.PLAY, Bound.SERVER, ::ResourcePackStatus)
}
