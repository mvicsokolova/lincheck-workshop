import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.paramgen.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.junit.*
import java.util.concurrent.*

@Param(name = "key", gen = IntGen::class, conf = "1:3")
class MultiMapTest {
    private val map = MultiMap<Int, Int>()

    @Operation
    fun add(@Param(name = "key") key: Int, value: Int) = map.add(key, value)

    @Operation
    fun get(@Param(name = "key") key: Int) = map.get(key)

    @Test
    fun runModelCheckingTest() = ModelCheckingOptions()
        .invocationsPerIteration(Int.MAX_VALUE)
        .addGuarantee(forClasses(ConcurrentHashMap::class.qualifiedName!!).allMethods().treatAsAtomic())
        .check(this::class)
}