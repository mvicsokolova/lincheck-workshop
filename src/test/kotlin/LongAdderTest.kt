import org.junit.*
import java.util.concurrent.atomic.*

class LongAdderTest {
    private val counter = LongAdder() // initial state

    fun inc(): Unit = TODO("delegate to increment() on LongAdder")
    fun dec(): Unit = TODO("delegate to decrement() on LongAdder")
    fun sum(): Int = TODO("delegate to sum() on LongAdder")

    @Test
    fun runStressTest(): Unit = TODO("run test in the stress mode")

    @Test
    fun runModelCheckingTest(): Unit = TODO("run test in the model checking mode")
}