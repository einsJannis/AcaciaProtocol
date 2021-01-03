package dev.einsjannis.acacia.protocol.primitives.chat

data class ChatClickEvent(
    var openUrl: String,
    var runCommand: String,
    var suggestCommand: String,
    var changePage: Int
)
