import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import kotlin.test.Test

class StressLincheckTest {
    private val queue = MichaelScottQueue<Int>()

    @Operation
    fun enqueue(value: Int) {
        queue.enqueue(value)
    }

    @Operation
    fun dequeue(): Int? {
        return queue.dequeue()
    }

    @Operation
    fun isEmpty(): Boolean {
        return queue.isEmpty()
    }

    @Test
    fun stressTest() = StressOptions()
        .iterations(100)
        .invocationsPerIteration(1000)
        .check(this::class)

}
