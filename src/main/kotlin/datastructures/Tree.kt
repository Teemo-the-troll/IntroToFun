package datastructures

typealias Visitor<T> = (TreeNode<T>) -> Unit
data class TreeNode<T>(var value: T) {
    val children = mutableListOf<TreeNode<T>>()

    fun link(toLink: TreeNode<T>) {
        this.children.add(toLink)
    }

    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    fun forEachLevel(visit: Visitor<T>) {
        visit(this)                                     // visits root
        val queue = ArrayDeque<TreeNode<T>>()       // new queue?
        children.forEach {
            queue.add(it)                           // add every child of root to queue
        }
        var node = queue.removeFirstOrNull()                      // take first in queue
        while (node != null) {                          // until node is not null
            visit(node)                                 // visit node
            node.children.forEach { queue.add(it) } // for every node, enqueue its children
            node = queue.removeFirstOrNull()                      // take first node of queue
        }
    }

    fun find(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null

        forEachDepthFirst {
            if (it.value == value) {
                result = it
            }
        }
        return result;
    }

}
