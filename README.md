# Simple Logging Facade for Kotlin (SLF4K)

This is a multiplatform library analog of famous SLF4J from JVM world.

The main goal of this library is to be a standard for logging inside libraries.

By default, without any implementation provided no output performed by facade. 

Consider this example:

```kotlin
import org.slf4k.*

class SomeService {
    companion object {
        // get logger from class with a class qualified name
        private val logger = SomeService::class.logger
    }
    fun someAction() {
        logger.debug { "Some action" }
    }
}

fun main() {
    // initialize logger with some implementation
    LoggerFactory.setImplementation(SomeLoggerImplementation(LogLevel.INFO))
  
    try {
        // some time after create logger
        val logger = LoggerFactory.create("main")
        
        // perform kotlin optimized logging
        logger.info { "Application started" }
        
        // of course you can use ordinary SLF4J-style logging
        if (logger.isDebugEnabled) {
            logger.debug("Some debug info")
        }
        
    } catch (e: Throwable) {
        // logging errors as usual
        logger.error(e) { "Unhandled exception" }
    }
}
```