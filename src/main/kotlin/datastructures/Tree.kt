package datastructures

typealias Visitor<T> = (TreeNode<T>) -> Unit
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
