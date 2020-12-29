package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Respawn : Packet() {
    var dimension by nbtTag()
    var worldName by id()
    var hashedSeed by long()
    var gamemode by ubyte()
    var previousGamemode by ubyte()
    var isDebug by bool()
    var isFlat by bool()
    var copyMetadata by bool()
    companion object : PacketMeta<Respawn>(0x39, ConnectionState.PLAY, Bound.CLIENT, ::Respawn)
}
