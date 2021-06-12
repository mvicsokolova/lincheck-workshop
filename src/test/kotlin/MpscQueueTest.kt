import org.jctools.queues.atomic.*
import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.junit.*

// TODO specify OpGroupConfig for the group of consuming operations here
class MpscQueueTest {
    private val queue = MpscLinkedAtomicQueue<Int>()

    @Operation
    fun offer(x: Int) = queue.offer(x)

    @Operation // TODO this consuming operation should be called from one thread, use "group" parameter
    fun poll(): Int? = queue.poll()

    @Operation // TODO this consuming operation should be called from one thread, use "group" parameter
    fun peek(): Int? = queue.peek()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions().check(this::class)
}