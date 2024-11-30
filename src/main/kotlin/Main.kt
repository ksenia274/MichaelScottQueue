import java.util.concurrent.atomic.AtomicReference

class MichaelScottQueue<E> {
    private class Node<E>(val value: E?) {
        val next = AtomicReference<Node<E>?>(null)
    }

    private val head = AtomicReference<Node<E>>(Node(null)) // "Пустой" узел
    private val tail = AtomicReference<Node<E>>(head.get())

    fun enqueue(value: E) {
        val newNode = Node(value)
        while (true) {
            val currentTail = tail.get()
            val tailNext = currentTail.next.get()

            if (currentTail == tail.get()) { // Проверяем, не изменился ли хвост
                if (tailNext == null) { // Если хвост указывает на последний элемент
                    if (currentTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(currentTail, newNode) // Обновляем хвост
                        return
                    }
                } else {
                    // Если есть следующий элемент, обновляем хвост
                    tail.compareAndSet(currentTail, tailNext)
                }
            }
        }
    }

    fun dequeue(): E? {
        while (true) {
            val currentHead = head.get()
            val currentTail = tail.get()
            val headNext = currentHead.next.get()

            if (currentHead == head.get()) { // Проверяем, не изменился ли голова
                if (currentHead == currentTail) { // Если голова и хвост совпадают
                    if (headNext == null) {
                        return null // Очередь пуста
                    }
                    // Обновляем хвост, если он указывает на старую голову
                    tail.compareAndSet(currentTail, headNext)
                } else {
                    // Извлекаем элемент из очереди
                    val value = headNext?.value
                    if (head.compareAndSet(currentHead, headNext)) {
                        return value
                    }
                }
            }
        }
    }

    fun isEmpty(): Boolean {
        return head.get().next.get() == null
    }
}

fun main() {
    val queue = MichaelScottQueue<Int>()

    // Пример использования очереди
    queue.enqueue(1)
    queue.enqueue(2)
    println(queue.dequeue()) // Вывод: 1
    println(queue.dequeue()) // Вывод: 2
    println(queue.dequeue()) // Вывод: null (очередь пуста)
}