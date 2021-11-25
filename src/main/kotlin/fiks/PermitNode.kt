package fiks

typealias Visitor<T> = (PermitNode<T>) -> Unit
data class PermitNode<T>(var value: T, var parent: PermitNode<T>? = null) {
    val children = mutableListOf<PermitNode<T>>()

    fun link(toLink: PermitNode<T>) {
        toLink.parent = this
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
        val queue = ArrayDeque<PermitNode<T>>()       // new queue?
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

    fun find(value: T): PermitNode<T>? {
        var result: PermitNode<T>? = null

        forEachDepthFirst {
            if (it.value == value) {
                result = it
            }
        }
        return result;
    }

    fun ancestorExists(value: T): Boolean {
        val queue = ArrayList<T>()
        var tempParent = parent;
        while (tempParent != null) {
            queue.add(tempParent.value)
            tempParent = tempParent.parent
        }
        return queue.contains(value)
    }

}
