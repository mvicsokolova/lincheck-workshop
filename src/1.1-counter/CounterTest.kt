import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.stress.*
import org.junit.*

class CounterTest {
    private val c = Counter() // the initial state

    // Operations on the Counter
    @Operation
    fun addAndGet(delta: Int) = c.addAndGet(delta)
    @Operation
    fun get() = c.get()

    @Test // stress test
    fun test() = StressOptions().check(this::class)
}