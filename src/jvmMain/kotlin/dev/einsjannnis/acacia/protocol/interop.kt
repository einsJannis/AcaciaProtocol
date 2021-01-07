package dev.einsjannnis.acacia.protocol

import java.util.UUID as JvmUUID
import dev.einsjannis.acacia.protocol.primitives.UUID as ProtocolUUID

fun ProtocolUUID.toJvmUUID(): JvmUUID = JvmUUID(this.upper, this.lower)

fun JvmUUID.toProtocolUUID(): ProtocolUUID = ProtocolUUID(this.mostSignificantBits, this.leastSignificantBits)
