package dev.einsjannis.acacia.protocol.primitives.chat

data class ChatHoverEvent (
    var showText: StringComponent,
    var showItem: String,
    var showEntity: String
)
