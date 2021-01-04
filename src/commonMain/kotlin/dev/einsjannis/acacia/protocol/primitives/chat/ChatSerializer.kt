package dev.einsjannis.acacia.protocol.primitives.chat

import kotlinx.serialization.json.*

object ChatSerializer {

    val json = Json {
        encodeDefaults = false
        prettyPrint = false
        isLenient = true
    }

    fun fromString(value: String): ChatComponent {
        val obj = json.parseToJsonElement(value) as JsonObject
        return parseObject(obj)
    }

    private fun parseObject(obj: JsonObject): ChatComponent {
        val bold = obj["bold"]?.jsonPrimitive?.boolean
        val italic = obj["italic"]?.jsonPrimitive?.boolean
        val underlined = obj["underlined"]?.jsonPrimitive?.boolean
        val strikethrough = obj["strikethrough"]?.jsonPrimitive?.boolean
        val obfuscated = obj["obfuscated"]?.jsonPrimitive?.boolean
        val color = obj["color"]?.jsonPrimitive?.content
        val insertion = obj["insertion"]?.jsonPrimitive?.content
        val clickEvent = obj["click_event"]?.jsonObject?.let { parseClickEvent(it) }
        val hoverEvent = obj["hover_event"]?.jsonObject?.let { parseHoverEvent(it) }
        val extra = obj["extra"]?.jsonArray?.map { parseObject(it.jsonObject) }
        val text = obj["text"]?.jsonPrimitive?.content
        if (text != null) {
            return StringComponent(
                text,
                bold,
                italic,
                underlined,
                strikethrough,
                obfuscated,
                insertion,
                color,
                clickEvent,
                hoverEvent,
                extra
            )
        }
        val translate = obj["translate"]?.jsonPrimitive?.content
        if (translate != null) {
            return TranslationComponent(
                translate,
                bold,
                italic,
                underlined,
                strikethrough,
                obfuscated,
                insertion,
                color,
                clickEvent,
                hoverEvent,
                extra
            )
        }
        val keybind = obj["keybind"]?.jsonPrimitive?.content
        if (keybind != null) {
            return KeybindComponent(
                keybind,
                bold,
                italic,
                underlined,
                strikethrough,
                obfuscated,
                insertion,
                color,
                clickEvent,
                hoverEvent,
                extra
            )
        }
        val score = obj["score"]?.jsonPrimitive?.content
        val name = obj["name"]?.jsonPrimitive?.content
        val objective = obj["name"]?.jsonPrimitive?.content
        val value = obj["name"]?.jsonPrimitive?.content
        if (score != null) {
            if (name == null || objective == null) throw TODO()
            return ScoreComponent(
                score,
                name,
                objective,
                value,
                bold,
                italic,
                underlined,
                strikethrough,
                obfuscated,
                color,
                insertion,
                clickEvent,
                hoverEvent,
                extra
            )
        }
        val selector = obj["selector"]?.jsonPrimitive?.content
        if (selector != null) {
            return SelectorComponent(
                selector,
                bold,
                italic,
                underlined,
                strikethrough,
                obfuscated,
                color,
                insertion,
                clickEvent,
                hoverEvent,
                extra
            )
        }
        throw TODO()
    }

    private fun parseHoverEvent(obj: JsonObject): ChatHoverEvent = ChatHoverEvent(
        obj["show_text"]?.jsonObject?.let { parseObject(it) as? StringComponent ?: throw TODO() },
        obj["show_item"]?.jsonPrimitive?.content,
        obj["show_entity"]?.jsonPrimitive?.content
    ) // TODO exclusivity

    private fun parseClickEvent(obj: JsonObject): ChatClickEvent = ChatClickEvent(
        obj["open_url"]?.jsonPrimitive?.content,
        obj["run_command"]?.jsonPrimitive?.content,
        obj["suggest_command"]?.jsonPrimitive?.content,
        obj["change_page"]?.jsonPrimitive?.int,
    )

    fun toString(value: ChatComponent): String = when (value) {
        is KeybindComponent -> json.encodeToString(KeybindComponent.serializer(), value)
        is ScoreComponent -> json.encodeToString(ScoreComponent.serializer(), value)
        is SelectorComponent -> json.encodeToString(SelectorComponent.serializer(), value)
        is StringComponent -> json.encodeToString(StringComponent.serializer(), value)
        is TranslationComponent -> json.encodeToString(TranslationComponent.serializer(), value)
        else -> throw TODO()
    }

}
