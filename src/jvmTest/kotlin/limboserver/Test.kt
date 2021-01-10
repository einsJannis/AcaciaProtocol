package limboserver

import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.io.net.Server
import dev.einsjannis.acacia.protocol.packet.handshaking.serverbound.Handshake
import dev.einsjannis.acacia.protocol.packet.login.clientbound.LoginSuccess
import dev.einsjannis.acacia.protocol.packet.login.serverbound.LoginStart
import dev.einsjannis.acacia.protocol.primitives.UUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ClientData(var username: String? = null) {
}

class Test(val scope: CoroutineScope, ip: String, port: Int) {
    val server = Server(scope, ip, port, ::ClientData)

    fun run() {
        server.run()
        scope.launch {
            for ((client, packet) in server.incomingPackets) {
                when (packet) {
                    is Handshake -> client.connectionState = packet.nextState
                    is LoginStart -> {
                        client.data.username = packet.name
                        client.send(LoginSuccess().also {
                            it.username = packet.name
                            it.uniqueId = UUID(0L, 0L)
                        })
                        client.connectionState = ConnectionState.PLAY
                    }

                }
            }
        }
    }
}
