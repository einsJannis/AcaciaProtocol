package dev.einsjannis

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.promise

actual fun <T> CoroutineScope.runMultiplatformBlocking(block: suspend () -> T): dynamic =
    promise { block() }.then { return@then it }
