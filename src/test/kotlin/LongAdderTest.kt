import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.jetbrains.kotlinx.lincheck.strategy.stress.*
import org.junit.*
import java.util.concurrent.atomic.*

class LongAdderTest {
    private val counter = LongAdder() // initial state

    @Operation
    fun inc() = counter.increment()

    @Operation
    fun dec() = counter.decrement()

    @Operation
    fun sum() = counter.sum()

    @Test
    fun runModelCheckingTest(): Unit = ModelCheckingOptions()
        .verboseTrace()
        .check(this::class)
}