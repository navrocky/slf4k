import org.slf4k.Logger
import org.slf4k.LoggerImplementation

actual class TestLoggerImplementation actual constructor(
    actual val logLevel: LogLevel
) : LoggerImplementation {

    private val mutableList = mutableListOf<LogMessage>()

    actual val messages: List<LogMessage>
        get() = mutableList

    actual fun addMessage(msg: LogMessage) {
        mutableList.add(msg)
    }

    override fun createLogger(name: String): Logger =
        TestLogger(name, this)
}