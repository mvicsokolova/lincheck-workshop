import org.jctools.queues.atomic.*
import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.junit.*

@OpGroupConfig(name = "consumers", nonParallel = true)
class MpscQueueTest {
    private val queue = MpscLinkedAtomicQueue<Int>()

    @Operation
    fun offer(x: Int) = queue.offer(x)

    @Operation(group = "consumers")
    fun poll(): Int? = queue.poll()

    @Operation(group = "consumers")
    fun peek(): Int? = queue.peek()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions().check(this::class)
}