import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import kotlin.test.Test
class CheckIsEmptyLincheckTest {

    @Operation
    fun checkIsEmpty() {
        val queue = MichaelScottQueue<Int>()

        assert(queue.isEmpty()) { "Queue should be empty initially." }

        queue.enqueue(1)
        assert(!queue.isEmpty()) { "Queue should not be empty after enqueuing an element." }
        queue.dequeue()
        assert(queue.isEmpty()) { "Queue should be empty after dequeuing the only element." }
    }


    @Test
    fun checkIsEmptyTest() = ModelCheckingOptions()
        .invocationsPerIteration(1000)
        .check(this::class)

}