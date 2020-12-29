package dev.einsjannis.acacia.protocol.packet.play.clientbound

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.PacketMeta

class Teams : Packet() {
    var teamName by string()
    var mode by byte()
    var teamDisplayName by chat().onlyIf({ mode == 0.toByte() || mode == 2.toByte() }, {  })
    var friendlyFlags by byte().onlyIf({ mode == 0.toByte() || mode == 2.toByte() }, {  })
    var nameTagVisibility by string().onlyIf({ mode == 0.toByte() || mode == 2.toByte() }, {  })
    var collisionRule by string().onlyIf({ mode == 0.toByte() || mode == 2.toByte() }, {  })
    var teamColor by varInt().onlyIf({ mode == 0.toByte() || mode == 2.toByte() }, {  })
    var teamPrefix by chat().onlyIf({ mode == 0.toByte() || mode == 2.toByte() }, {  })
    var teamSuffix by chat().onlyIf({ mode == 0.toByte() || mode == 2.toByte() }, {  })
    var entityCount by varInt().onlyIf({ mode == 0.toByte() || mode == 3.toByte() || mode == 4.toByte() }, {  })
    var entities by string().array({ entityCount!! }, { entityCount = it }).onlyIf({ mode == 0.toByte() || mode == 3.toByte() || mode == 4.toByte() }, {  })
    companion object : PacketMeta<Teams>(0x4C, ConnectionState.PLAY, Bound.CLIENT, ::Teams)
}
