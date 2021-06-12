import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.jetbrains.kotlinx.lincheck.strategy.stress.*
import org.junit.*
import java.util.concurrent.atomic.*

class LongAdderTest {
    private val counter = LongAdder() // initial state

    @Operation // TODO restrict delta parameters to [1:5]
    fun add(delta: Long) = counter.add(delta)

    @Operation
    fun sum() = counter.sum()

    @Test
    fun runModelCheckingTest(): Unit = ModelCheckingOptions().check(this::class)
}