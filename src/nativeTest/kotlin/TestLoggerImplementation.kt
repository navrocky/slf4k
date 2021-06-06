import org.slf4k.Logger
import org.slf4k.LoggerImplementation
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze

actual class TestLoggerImplementation actual constructor(
    actual val logLevel: LogLevel
) : LoggerImplementation {
    private val mutableMessages = AtomicReference<List<LogMessage>>(emptyList())
    actual val messages: List<LogMessage> get() = mutableMessages.value

    actual fun addMessage(msg: LogMessage) {
        val newList = mutableMessages.value + msg
        newList.freeze()
        mutableMessages.compareAndSet(mutableMessages.value, newList)
    }

    override fun createLogger(name: String): Logger =
        TestLogger(name, this)
}
