import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import kotlin.test.Test
class CheckSingleElementLincheckTest {

    @Operation
    fun checkSingleElement() {
        val queue = MichaelScottQueue<Int>()
        queue.enqueue(42)
        assert(queue.dequeue() == 42) { "Dequeued value is incorrect." }
        assert(queue.isEmpty()) { "Queue should be empty after dequeuing the only element." }
    }


    @Test
    fun checkSingleElementTest() = ModelCheckingOptions()
        .invocationsPerIteration(1000)
        .check(this::class)

}