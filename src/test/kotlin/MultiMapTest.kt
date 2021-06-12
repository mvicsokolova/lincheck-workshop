import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.Test

// TODO: configure "key" parameter generation within a smaller range
class MultiMapTest {
    private val map = MultiMap<Int, Int>()

    @Operation
    fun add(key: Int, value: Int) = map.addBroken(key, value)

    @Operation
    fun get(key: Int) = map.get(key)

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions()
        // TODO: add atomicity guarantees for the methods of the underlying ConcurrentHashMap
        .check(this::class.java)
}