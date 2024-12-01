import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import kotlin.test.Test
class CheckOrderLincheckTest {

    @Operation
    fun checkOrder() {
        val queue = MichaelScottQueue<Int>()

        val expectedOrder = mutableListOf<Int>()
        for (i in 1..10) {
            queue.enqueue(i)
            expectedOrder.add(i)
        }
        assert(queue.dequeue() == expectedOrder[0]) { "Order of dequeued elements is incorrect." }
    }



    @Test
    fun checkOrderTest() = ModelCheckingOptions()
        .invocationsPerIteration(1000)
        .check(this::class)

}