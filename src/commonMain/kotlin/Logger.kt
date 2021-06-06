package org.slf4k

typealias MessageProvider = () -> String

interface Logger {
    val isTraceEnabled: Boolean
    fun trace(msg: String, throwable: Throwable? = null)
    fun trace(throwable: Throwable? = null, msg: MessageProvider)

    val isDebugEnabled: Boolean
    fun debug(msg: String, throwable: Throwable? = null)
    fun debug(throwable: Throwable? = null, msg: MessageProvider)

    val isInfoEnabled: Boolean
    fun info(msg: String, throwable: Throwable? = null)
    fun info(throwable: Throwable? = null, msg: MessageProvider)

    val isWarnEnabled: Boolean
    fun warn(msg: String, throwable: Throwable? = null)
    fun warn(throwable: Throwable? = null, msg: MessageProvider)

    val isErrorEnabled: Boolean
    fun error(msg: String, throwable: Throwable? = null)
    fun error(throwable: Throwable? = null, msg: MessageProvider)
}
