import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.strategy.stress.*
import org.jetbrains.kotlinx.lincheck.verifier.*
import org.junit.*
import java.util.concurrent.*

class ConcurrentLinkedDequeTest : VerifierState() {
    private val deque = ConcurrentLinkedDeque<Int>()

    @Operation
    fun addFirst(value: Int) = deque.addFirst(value)

    @Operation
    fun addLast(value: Int) = deque.addLast(value)

    @Operation
    fun getFirst() = deque.peekFirst()

    @Operation // this operation is non-linearizable!
    fun getLast() = deque.peekLast()

    @Operation
    fun pollFirst() = deque.pollFirst()

    @Operation
    fun pollLast() = deque.pollLast()

    override fun extractState() = deque.toList()

    @Test
    fun runModelCheckingTest() = StressOptions()
        .check(ConcurrentLinkedDequeTest::class)
}