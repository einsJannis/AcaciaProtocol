package dev.einsjannis.acacia.protocol.primitives.chat

import kotlinx.serialization.Serializable

@Serializable
data class ChatHoverEvent (
    var showText: StringComponent?,
    var showItem: String?,
    var showEntity: String?
)
