package dev.einsjannis.acacia.protocol.packet.play.serverbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta
import dev.einsjannis.acacia.protocol.types.SkinPart
import dev.einsjannis.acacia.protocol.types.ChatMode
import dev.einsjannis.acacia.protocol.types.MainHand

class ClientSettings : Packet() {
    var locale by string()
    var viewDistance by byte()
    var chatMode by varInt().mapped({ ChatMode.values()[it] }, { it.ordinal })
    var chatColors by bool()
    var displayedSkinParty by ubyte().mapped({ SkinPart(it.toInt()) }, { it.value.toUByte() })
    var mainHand by varInt().mapped({ MainHand.values()[it] }, { it.ordinal })

    companion object : PacketMeta<ClientSettings>(0x05, ConnectionState.PLAY, Bound.SERVER, ::ClientSettings)
}
