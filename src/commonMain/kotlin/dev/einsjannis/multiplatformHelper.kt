package dev.einsjannis

import kotlinx.coroutines.CoroutineScope

expect fun <T> CoroutineScope.runMultiplatformBlocking(block: suspend () -> T): T
