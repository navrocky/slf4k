package org.slf4k

import kotlin.reflect.KClass

expect object LoggerFactory {
    fun create(name: String): Logger
    fun setImplementation(implementation: LoggerImplementation)
}

expect val KClass<*>.logger: Logger
