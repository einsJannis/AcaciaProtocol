package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.enumOrdinalMapping
import dev.einsjannis.acacia.protocol.types.Gamemode

class JoinGame : Packet() {
    var entityId by int()
    var isHardcore by bool()
    var gamemode by ubyte().mapped({ it.toInt() }, { it.toUByte() }).enumOrdinalMapping<Gamemode>()
    var previousGamemode by byte().mapped({ if (it == ((-1).toByte())) null else Gamemode.values()[it.toInt()] },
        { (it?.ordinal ?: -1).toByte() })
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
