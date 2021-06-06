package org.slf4k

object NoopLogger : Logger {
    override val isTraceEnabled: Boolean = false
    override fun trace(msg: String, throwable: Throwable?) {}
    override fun trace(throwable: Throwable?, msg: MessageProvider) {}

    override val isDebugEnabled: Boolean = false
    override fun debug(msg: String, throwable: Throwable?) {}
    override fun debug(throwable: Throwable?, msg: MessageProvider) {}

    override val isInfoEnabled: Boolean = false
    override fun info(msg: String, throwable: Throwable?) {}
    override fun info(throwable: Throwable?, msg: MessageProvider) {}

    override val isWarnEnabled: Boolean = false
    override fun warn(msg: String, throwable: Throwable?) {}
    override fun warn(throwable: Throwable?, msg: MessageProvider) {}

    override val isErrorEnabled: Boolean = false
    override fun error(msg: String, throwable: Throwable?) {}
    override fun error(throwable: Throwable?, msg: MessageProvider) {}
}