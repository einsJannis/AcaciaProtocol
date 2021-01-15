package dev.einsjannis.acacia.protocol.io.net

import dev.einsjannis.acacia.protocol.Packet
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.ServerSocket
import io.ktor.network.sockets.aSocket
import io.ktor.util.InternalAPI
import io.ktor.util.network.NetworkAddress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

data class ClientIncomingPackage<CLIENTDATA, T : Packet>(val client: ServerClient<CLIENTDATA>, val packet: T)
class Server<CLIENTDATA>(
    val scope: CoroutineScope,
    val ip: String,
    val port: Int,
    val dataConstructor: ()->CLIENTDATA,
) {
    var serverSocket: ServerSocket? = null
    var running = false
    var job: Job? = null
    val connectedClients = mutableListOf<ServerClient<CLIENTDATA>>()
    val incomingPackets = Channel<ClientIncomingPackage<CLIENTDATA, *>>()

    @OptIn(InternalAPI::class)
    fun run() {
        if (running) return
        running = true
        job = scope.launch {
            serverSocket = aSocket(SelectorManager(scope.coroutineContext)).tcp().bind(NetworkAddress(ip, port))
            while (running) listen()
            serverSocket!!.close()
        }
    }

    private suspend fun listen() {
        val socket = serverSocket!!.accept()
        val client = ServerClient(scope, socket, this@Server, dataConstructor())
        connectedClients.add(client)
        client.run()
    }

    fun shutdownGracefully(sendDisconnect: Boolean = true, disconnectMessage: String = "Server shut down") {
        running = false
        serverSocket?.close()
        connectedClients.forEach { it.shutdownGracefully(sendDisconnect, disconnectMessage) }
    }

}
