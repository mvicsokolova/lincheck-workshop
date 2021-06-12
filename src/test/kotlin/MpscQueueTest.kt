import org.jctools.queues.atomic.MpscLinkedAtomicQueue
import org.jetbrains.kotlinx.lincheck.annotations.OpGroupConfig
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.Test

/**
 * TODO: 1. Define sequential specification for this queue
 *       2. For faster verification set state equivalency relation:
 *          - extend VerifierState class
 *          - override extractState() function
 */
@OpGroupConfig(name = "consumer", nonParallel = true)
public class MpscQueueTest {
    private val queue = MpscLinkedAtomicQueue<Int>()

    @Operation
    public fun offer(x: Int) = queue.offer(x)

    @Operation(group = "consumer")
    public fun poll(): Int? = queue.poll()

    @Operation(group = "consumer")
    public fun peek(): Int? = queue.peek()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions().check(this::class)
}