package dev.einsjannis

import kotlinx.coroutines.CoroutineScope

actual fun <T> CoroutineScope.runMultiplatformBlocking(block: suspend () -> T): T =
    throw RuntimeException("Platform not supported") // TODO
