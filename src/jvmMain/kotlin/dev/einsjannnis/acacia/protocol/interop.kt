package dev.einsjannnis.acacia.protocol

import dev.einsjannis.UUID as ProtocolUUID
import java.util.UUID as JvmUUID

fun ProtocolUUID.toJvmUUID(): JvmUUID = JvmUUID(this.upper, this.lower)
inline val JvmUUID.mostInsignificantBits get() = this.leastSignificantBits
fun JvmUUID.toProtocolUUID(): ProtocolUUID = ProtocolUUID(this.mostSignificantBits, this.mostInsignificantBits)
