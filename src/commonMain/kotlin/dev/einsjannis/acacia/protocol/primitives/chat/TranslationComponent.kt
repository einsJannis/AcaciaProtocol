package dev.einsjannis.acacia.protocol.primitives.chat

data class TranslationComponent(
    var translate: String,
    override var bold: Boolean? = null,
    override var italic: Boolean? = null,
    override var underlined: Boolean? = null,
    override var strikethrough: Boolean? = null,
    override var obfuscated: Boolean? = null,
    override var color: String? = null,
    override var insertion: String? = null,
    override var clickEvent: ChatClickEvent? = null,
    override var hoverEvent: ChatHoverEvent? = null,
    override var extra: List<ChatComponent>? = null
) : ChatComponent
