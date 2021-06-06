package org.slf4k

interface LoggerImplementation {
    fun createLogger(name: String): Logger
}