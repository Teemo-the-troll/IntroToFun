package datastructures

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

   // a.find(2)
    println(a.forEachLevel())
}

fun TreeNode<Int>.forEachLevel(): List<Int> {
    val output = sequence<Int> {
        yield(this@forEachLevel.value)                                     // visits root
        val queue = ArrayListQueue<TreeNode<Int>>()       // new queue?
        children.forEach {
            queue.enqueue(it)                           // add every child of root to queue
        }
        var node = queue.dequeue()                      // take first in queue
        while (node != null) {                          // until node is not null
            yield(node.value)                                // visit node
            node.children.forEach { queue.enqueue(it) } // for every node, enqueue its children
            node = queue.dequeue()                      // take first node of queue
        }
    }
    return output.toList()
}
