package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Camera : Packet() {
    var cameraId by varInt()
    companion object : PacketMeta<Camera>(0x3E, ConnectionState.PLAY, Bound.CLIENT, ::Camera)
}
