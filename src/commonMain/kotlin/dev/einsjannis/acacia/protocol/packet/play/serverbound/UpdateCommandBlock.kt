package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.primitives.FlagBit
import dev.einsjannis.acacia.protocol.primitives.Flags
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.bitFlag
import dev.einsjannis.acacia.protocol.enumOrdinalMapping

enum class CommandBlockMode {
    SEQUENCE,
    AUTO,
    REDSTONE,
}

class CommandBlockFlags(value: Int = 0) : Flags(value) {
    var trackOutput by FlagBit(0)
    var isConditional by FlagBit(1)
    var automatic by FlagBit(2)
}

class UpdateCommandBlock : Packet() {
    var location by position()
    var command by string()
    var mode by varInt().enumOrdinalMapping<CommandBlockMode>()
    var flags by byte().mapped({ it.toInt() }, { it.toByte() }).bitFlag(::CommandBlockFlags)

    companion object : PacketMeta<UpdateCommandBlock>(0x26, ConnectionState.PLAY, Bound.SERVER, ::UpdateCommandBlock)
}
