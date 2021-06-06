import org.slf4k.LoggerFactory
import org.slf4k.logger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoggingTest {

    @Test
    fun initialization_and_logging_with_Noop_Logger() {
        val logger = LoggerFactory.create("test")
        logger.debug { "Some debug info" }
    }

    @Test
    fun test_logging_on_all_levels() {
        val loggerBuilder = TestLoggerImplementation(logLevel = LogLevel.INFO)
        LoggerFactory.setImplementation(loggerBuilder)
        val logger = LoggerFactory.create("test")
        var counter = 0
        logger.trace {
            counter++
            "Trace"
        }
        assertEquals(0, counter)
        logger.debug {
            counter++
            "Debug"
        }
        assertEquals(0, counter)
        logger.info {
            counter++
            "Info"
        }
        assertEquals(1, counter)
        logger.warn {
            counter++
            "Warn"
        }
        assertEquals(2, counter)
        logger.error {
            counter++
            "Error"
        }
        assertEquals(3, counter)
        assertEquals(3, loggerBuilder.messages.size)
    }

    class TestClass

    @Test
    fun get_logger_name_from_class() {
        val loggerBuilder = TestLoggerImplementation(logLevel = LogLevel.INFO)
        LoggerFactory.setImplementation(loggerBuilder)
        val logger = TestClass::class.logger
        logger.info("Test")

        loggerBuilder.messages.first().also {
            assertTrue(it.name.endsWith("TestClass"))
            assertEquals("Test", it.message)
        }
    }

}