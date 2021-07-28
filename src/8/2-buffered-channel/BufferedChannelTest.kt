import kotlinx.coroutines.channels.Channel
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.jetbrains.kotlinx.lincheck.verifier.VerifierState
import org.junit.Test

class BufferedChannelTest : VerifierState() {
    private val ch = Channel<Int>(3)

    fun send(value: Int): Unit = TODO ("delegate to suspend send method on Channel")

    fun receive(): Unit = TODO("delegate to suspend receive method on Channel")

    fun close(): Unit = TODO("delegate to close method on Channel")

    // Hint: you can retrieve the elements from the buffered channel via poll()
    override fun extractState(): Unit = TODO("define state of the buffered channel")

    @Test
    fun runStressTest() = StressOptions().check(this::class)

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions().check(this::class)
}