import org.jctools.maps.*
import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.paramgen.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.jetbrains.kotlinx.lincheck.strategy.stress.*
import org.junit.*

@Param(name = "key", gen = LongGen::class, conf = "1:5")
class NonBlockingHashMapTest {
    private val map = NonBlockingHashMapLong<Int>()

    @Operation
    fun get(@Param(name = "key") x: Long) = map.get(x)

    @Operation
    fun put(@Param(name = "key") x: Long, v: Int) = map.put(x, v)

    @Test
    fun stressTest() = StressOptions().check(this::class)

    @Test
    fun modelCheckingTest() = ModelCheckingOptions().check(this::class)
}