package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.*

class Explosion : Packet() {
    var x by float()
    var y by float()
    var z by float()
    var strength by float()
    var recordCount by int()
    var records by `object`(::Record).array(::recordCount)
    var playerMotionX by float()
    var playerMotionY by float()
    var playerMotionZ by float()
    companion object : PacketMeta<Explosion>(0x1B, ConnectionState.PLAY, Bound.CLIENT, ::Explosion)
}
