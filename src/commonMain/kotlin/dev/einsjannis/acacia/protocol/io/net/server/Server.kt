package dev.einsjannis.acacia.protocol.io.net.server

import dev.einsjannis.acacia.protocol.Bound
import dev.einsjannis.acacia.protocol.ConnectionState
import dev.einsjannis.acacia.protocol.Packet
import dev.einsjannis.acacia.protocol.io.ByteArrayReader
import dev.einsjannis.acacia.protocol.readVarInt
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.util.*
import io.ktor.util.network.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Server(
    val ip: String,
    val port: Int
) {
    var serverSocket: ServerSocket? = null
    @OptIn(InternalAPI::class)
    var serverSelectorManager: SelectorManager = TODO()
    var running = false
    suspend fun run() {
        GlobalScope.run {
            if (running) throw TODO()
            running = true
            serverSocket = aSocket(serverSelectorManager).tcp().bind(NetworkAddress(ip, port))
            while (running) {
                val socket = serverSocket!!.accept()
                launch { Client(socket).handle() }
            }
        }
    }
}
