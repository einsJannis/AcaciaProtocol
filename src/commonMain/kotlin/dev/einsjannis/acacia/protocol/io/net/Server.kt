package dev.einsjannis.acacia.protocol.io.net

import dev.einsjannis.acacia.protocol.Bound
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.util.*
import io.ktor.util.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Server(
    val scope: CoroutineScope,
    val ip: String,
    val port: Int
) {
    var serverSocket: ServerSocket? = null
    var running = false
    var job: Job? = null
    val connectedClients = mutableListOf<Client>()

    @OptIn(InternalAPI::class)
    fun run() {
        if (running) throw TODO()
        running = true
        job = scope.launch {
            serverSocket = aSocket(SelectorManager(scope.coroutineContext)).tcp().bind(NetworkAddress(ip, port))
            while (running) {
                val socket = serverSocket!!.accept()
                val c = Client(scope, socket, Bound.SERVER)
                connectedClients.add(c)
                c.run()
            }
            serverSocket!!.close()
        }
    }

    fun shutdownGracefully() {
        running = false
    }

}
