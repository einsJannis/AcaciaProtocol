package dev.einsjannis.acacia.protocol.primitives.chat

data class ScoreComponent(
    var score: String,
    var name: String,
    var objective: String,
    var value: String?,
    override var bold: Boolean,
    override var italic: Boolean,
    override var underlined: Boolean,
    override var strikethrough: Boolean,
    override var obfuscated: Boolean,
    override var color: String,
    override var insertion: String,
    override var clickEvent: ChatClickEvent,
    override var hoverEvent: ChatHoverEvent,
    override var extra: List<ChatComponent>?
) : ChatComponent
