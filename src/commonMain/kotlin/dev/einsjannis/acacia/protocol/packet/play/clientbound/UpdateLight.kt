package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class UpdateLight : Packet() {
    var x by varInt()
    var z by varInt()
    var trustEdges by bool()
    var skyLightMask by varInt()
    var blockLightMask by varInt()
    var emptySkyLightMask by varInt()
    var emptyBlockLightMask by varInt()
    var skyLightsSize by varInt()
    var skyLights by byteArray({ 2048 }, { if (it != 2048) throw TODO() }).array(::skyLightsSize)
    var blockLightsSize by varInt()
    var blockLights by byteArray({ 2048 }, { if (it != 2048) throw TODO() }).array(::blockLightsSize)
    companion object : PacketMeta<UpdateLight>(0x23, ConnectionState.PLAY, Bound.CLIENT, ::UpdateLight)
}
