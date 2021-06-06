import org.slf4k.Logger
import org.slf4k.LoggerImplementation
import org.slf4k.MessageProvider

enum class LogLevel {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR
}

data class LogMessage(
    val name: String,
    val level: LogLevel,
    val message: String,
    val throwable: Throwable?
)

class TestLogger(
    private val name: String,
    private val loggerBuilder: TestLoggerImplementation
) : Logger {

    override val isTraceEnabled: Boolean
        get() = loggerBuilder.logLevel.ordinal <= LogLevel.TRACE.ordinal

    private fun print(level: LogLevel, msg: String, throwable: Throwable?) {
        loggerBuilder.addMessage(LogMessage(name, level, msg, throwable))
    }

    private fun write(level: LogLevel, msg: String, throwable: Throwable?) {
        if (level.ordinal >= loggerBuilder.logLevel.ordinal)
            print(level, msg, throwable)
    }

    private fun write(level: LogLevel, throwable: Throwable?, msg: MessageProvider) {
        if (level.ordinal >= loggerBuilder.logLevel.ordinal)
            print(level, msg(), throwable)
    }

    override fun trace(msg: String, throwable: Throwable?) {
        write(LogLevel.TRACE, msg, throwable)
    }

    override fun trace(throwable: Throwable?, msg: MessageProvider) {
        write(LogLevel.TRACE, throwable, msg)
    }

    override val isDebugEnabled: Boolean
        get() = loggerBuilder.logLevel.ordinal <= LogLevel.DEBUG.ordinal

    override fun debug(msg: String, throwable: Throwable?) {
        write(LogLevel.DEBUG, msg, throwable)
    }

    override fun debug(throwable: Throwable?, msg: MessageProvider) {
        write(LogLevel.DEBUG, throwable, msg)
    }

    override val isInfoEnabled: Boolean
        get() = loggerBuilder.logLevel.ordinal <= LogLevel.INFO.ordinal

    override fun info(msg: String, throwable: Throwable?) {
        write(LogLevel.INFO, msg, throwable)
    }

    override fun info(throwable: Throwable?, msg: MessageProvider) {
        write(LogLevel.INFO, throwable, msg)
    }

    override val isWarnEnabled: Boolean
        get() = loggerBuilder.logLevel.ordinal <= LogLevel.WARN.ordinal

    override fun warn(msg: String, throwable: Throwable?) {
        write(LogLevel.WARN, msg, throwable)
    }

    override fun warn(throwable: Throwable?, msg: MessageProvider) {
        write(LogLevel.WARN, throwable, msg)
    }

    override val isErrorEnabled: Boolean
        get() = loggerBuilder.logLevel.ordinal <= LogLevel.ERROR.ordinal

    override fun error(msg: String, throwable: Throwable?) {
        write(LogLevel.ERROR, msg, throwable)
    }

    override fun error(throwable: Throwable?, msg: MessageProvider) {
        write(LogLevel.ERROR, throwable, msg)
    }

}

expect class TestLoggerImplementation(
    logLevel: LogLevel
) : LoggerImplementation {
    val logLevel: LogLevel
    val messages: List<LogMessage>
    fun addMessage(msg: LogMessage)
}
