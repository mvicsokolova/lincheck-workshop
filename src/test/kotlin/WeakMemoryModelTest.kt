import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.strategy.stress.*
import org.junit.*

class WeakMemoryModelTest {
    var x = 0
    var y = 0

    fun t1(): Int {
        x = 1
        return y
    }

    fun t2(): Int {
        y = 1
        return x
    }

    @Test
    fun test() = StressOptions()
        .iterations(0)
        .addCustomScenario {
            parallel {
                thread { actor(::t1) }
                thread { actor(::t2) }
            }
        }
        .check(this::class)
}