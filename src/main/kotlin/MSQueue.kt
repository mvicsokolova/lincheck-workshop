import java.util.concurrent.atomic.AtomicReference

class MSQueue {
    private val head: AtomicReference<Node>
    private val tail: AtomicReference<Node>

    init {
        val dummyNode = Node(0)
        head = AtomicReference(dummyNode)
        tail = AtomicReference(dummyNode)
    }

    fun enqueue(x: Int) {
        val newTail = Node(x)
        while (true) { // CAS loop
            val curTail = tail.get()
            if (curTail.next.compareAndSet(null, newTail)) {
                // Node has been added -> move tail forward
                tail.compareAndSet(curTail, newTail)
                break
            } else {
                // Help other enqueue operations to move tail forward
                // Without this code, the algorithm becomes blocking
                //tail.compareAndSet(curTail, curTail.next.get())
            }
        }
    }

    fun dequeue(): Int? {
        while (true) { // CAS loop
            val curHead = head.get()
            val headNext = curHead.next.get() ?: return null
            if (head.compareAndSet(curHead, headNext)) {
                return headNext.value
            }
        }
    }

    class Node(val value: Int) {
        val next = AtomicReference<Node>(null)
    }
}