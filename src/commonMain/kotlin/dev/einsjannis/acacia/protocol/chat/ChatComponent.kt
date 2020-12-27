package dev.einsjannis.acacia.protocol.chat

interface ChatComponent {

    var bold: Boolean
    var italic: Boolean
    var underlined: Boolean
    var strikethrough: Boolean
    var obfuscated: Boolean
    var color: String
    var insertion: String
    var clickEvent: ChatClickEvent
    var hoverEvent: ChatHoverEvent
    var extra: List<ChatComponent>?

}
