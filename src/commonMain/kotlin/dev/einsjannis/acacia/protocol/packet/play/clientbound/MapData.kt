package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class MapData : Packet() {
    var mapId by varInt()
    var scale by byte()
    var trackingPosition by bool()
    var locked by bool()
    var iconCount by varInt()
    var columns by ubyte()
    var rows by ubyte().onlyIf({ columns > (0.toUByte()) }, {  })
    var x by ubyte().onlyIf({ columns > (0.toUByte()) }, {  })
    var z by ubyte().onlyIf({ columns > (0.toUByte()) }, {  })
    var lenght by varInt().onlyIf({ columns > (0.toUByte()) }, {  })
    var data by ubyte().array({ lenght!! }, { lenght = it }).onlyIf({ columns > (0.toUByte()) }, {  })
    companion object : PacketMeta<MapData>(0x25, ConnectionState.PLAY, Bound.CLIENT, ::MapData)
}
