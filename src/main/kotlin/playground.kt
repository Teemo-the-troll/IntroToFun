typealias Visitor<T> = (TreeNode<T>) -> Unit

fun main() {
    val a = TreeNode<Int>(0)
    val b = TreeNode<Int>(1)
    val c = TreeNode<Int>(2)
    val d = TreeNode<Int>(3)
    val e = TreeNode<Int>(4)
    val f = TreeNode<Int>(5)

    a.link(b)

    b.link(c)
    b.link(d)

    c.link(e)
    c.link(f)

    d.link(c)

  /*  c.link(e)
    c.link(f)*/

    a.forEachLevel { println(it.value) }
}

data class TreeNode<T>(var value: T) {
    var depth = 0
    val children = mutableListOf<TreeNode<T>>()


    fun link(toLink: TreeNode<T>) {
        toLink.depth = this.depth + 1
        this.children.add(toLink)
    }

    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)

        children.forEach {
            it.forEachDepthFirst(visit)

        }
    }

    fun forEachLevel(visit: Visitor<T>) {
        visit(this) // visits root
        val queue = ArrayListQueue<TreeNode<T>>() // new queue?
        children.forEach {
            queue.enqueue(it) // add every child of root to queue
        }
        var node = queue.dequeue() // take first in queue
        while (node != null) { // until node is not null
            visit(node) // visit node
            node.children.forEach { queue.enqueue(it) } // for every node, enqueue its children
            node = queue.dequeue() // take first node of queue
        }
    }

}


interface Queue<T> {
    val count: Int
    val isEmpty: Boolean
    fun peek(): T?
    fun enqueue(element: T): Boolean
    fun dequeue(): T?
}


class ArrayListQueue<T> : Queue<T> {
    private val storage = arrayListOf<T>()
    override val count: Int
        get() = storage.size

    override val isEmpty: Boolean
        get() = count == 0

    override fun peek(): T? {
        return storage.getOrNull(0)
    }

    override fun enqueue(element: T): Boolean {
        return storage.add(element)
    }

    override fun dequeue(): T? {
        return if (isEmpty) null else storage.removeAt(0)
    }

}
