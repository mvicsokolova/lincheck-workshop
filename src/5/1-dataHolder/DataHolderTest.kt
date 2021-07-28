import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.junit.*

@OpGroupConfig(name = "writer", nonParallel = true)
class DataHolderTest {
    private val dataHolder = DataHolder()

    @Operation(group = "writer")
    fun write(first: Long, second: Long) = dataHolder.write(first, second)

    @Operation
    fun read() = dataHolder.read()

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions()
        // TODO: check for obstruction freedom
        .check(this::class.java)
}