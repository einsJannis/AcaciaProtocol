package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class JoinGame : Packet() {
    var entityId by int()
    var isHardcore by bool()
    var gamemode by ubyte()
    var previousGamemode by ubyte()
    var worldCount by varInt()
    var worldNames by id().array(::worldCount)
    var dimensionCodec by nbtTag()
    var dimension by nbtTag()
    var worldName by id()
    var hashedSeed by long()
    var maxPlayers by varInt()
    var viewDistance by varInt()
    var reducedDebugInfo by bool()
    var enableRespawnScreen by bool()
    var isDebug by bool()
    var isFlat by bool()
    companion object : PacketMeta<JoinGame>(0x24, ConnectionState.PLAY, Bound.CLIENT, ::JoinGame)
}
