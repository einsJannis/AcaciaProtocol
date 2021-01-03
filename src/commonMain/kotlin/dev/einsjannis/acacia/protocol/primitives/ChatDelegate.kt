package dev.einsjannis.acacia.protocol.primitives

import dev.einsjannis.acacia.protocol.BaseDelegate
import dev.einsjannis.acacia.protocol.io.PrimitiveReader
import dev.einsjannis.acacia.protocol.io.PrimitiveWriter
import dev.einsjannis.acacia.protocol.primitives.chat.ChatComponent

class ChatDelegate : BaseDelegate<ChatComponent>() {
    override fun read(reader: PrimitiveReader): ChatComponent = reader.readChat()
    override fun write(writer: PrimitiveWriter, value: ChatComponent) = writer.writeChat(value)
}
