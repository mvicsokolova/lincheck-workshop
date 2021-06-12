import org.jctools.queues.atomic.*
import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.jetbrains.kotlinx.lincheck.verifier.*
import org.junit.*
import java.util.*

@OpGroupConfig(name = "consumer", nonParallel = true)
class MpscQueueTest {
    private val queue = MpscLinkedAtomicQueue<Int>()

    @Operation
    fun offer(x: Int) = queue.offer(x)

    @Operation(group = "consumer")
    fun poll(): Int? = queue.poll()

    @Operation(group = "consumer")
    fun peek(): Int? = queue.peek()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions()
        .sequentialSpecification(SequentialQueue::class.java)
        .check(this::class)
}

class SequentialQueue : VerifierState() {
    private val q = LinkedList<Int>()

    fun offer(x: Int) = q.offer(x)
    fun poll() = q.poll()
    fun peek() = q.peek()

    override fun extractState() = q.toList()
}