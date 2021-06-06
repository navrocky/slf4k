package org.slf4k

import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import kotlin.reflect.KClass

actual object LoggerFactory {
    private val builder = AtomicReference<LoggerImplementation?>(null)

    actual fun create(name: String): Logger =
        builder.value?.createLogger(name) ?: NoopLogger

    actual fun setImplementation(implementation: LoggerImplementation) {
        implementation.freeze()
        this.builder.compareAndSet(this.builder.value, implementation)
    }
}

actual val KClass<*>.logger: Logger
    get() = LoggerFactory.create(this.qualifiedName ?: "Unknown")