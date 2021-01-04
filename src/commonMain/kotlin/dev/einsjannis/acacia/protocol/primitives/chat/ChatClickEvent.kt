package dev.einsjannis.acacia.protocol.primitives.chat

import kotlinx.serialization.Serializable

@Serializable
data class ChatClickEvent(
    var openUrl: String?,
    var runCommand: String?,
    var suggestCommand: String?,
    var changePage: Int?
)
