package org.slf4k

import kotlin.reflect.KClass

actual object LoggerFactory {

    private var implementation: LoggerImplementation? = null

    actual fun create(name: String): Logger =
        implementation?.createLogger(name) ?: NoopLogger

    actual fun setImplementation(implementation: LoggerImplementation) {
        this.implementation = implementation
    }
}

actual val KClass<*>.logger: Logger
    get() = LoggerFactory.create(this.qualifiedName ?: "Unknown")