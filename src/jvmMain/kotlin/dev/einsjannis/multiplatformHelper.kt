package dev.einsjannis

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual fun <T> CoroutineScope.runMultiplatformBlocking(block: suspend () -> T): T =
    runBlocking { block() }
