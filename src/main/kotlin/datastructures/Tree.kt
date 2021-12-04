package datastructures

typealias TreeNodeVisitor<T> = (TreeNode<T>) -> Unit


data class TreeNode<T>(var value: T) {
    val children = mutableListOf<TreeNode<T>>()
    var parent: TreeNode<T>? = null

    fun link(toLink: TreeNode<T>) {
        children.add(toLink)
        toLink.parent = this
    }

    fun forEachDepthFirst(visit: TreeNodeVisitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    fun fromChildToRoot(visit: TreeNodeVisitor<T>) {
        visit(this)
        var parent = this.parent
        while (parent != null) {
            visit(parent)
            parent = parent.parent
        }
    }

    /**
     * @return List of paths, ranging from the leaf to root
     *
     * */
    fun getPaths(): List<TreePath<T>> {
        val result = mutableListOf<TreePath<T>>()
        for (leaf in getLeaves()) {
            val tempPath = TreePath<T>()
            leaf.fromChildToRoot { tempPath.addToPath(it) }
            result.add(tempPath)
        }
        return result
    }

    fun getLeaves(): List<TreeNode<T>> {
        val result = mutableListOf<TreeNode<T>>()
        forEachDepthFirst {
            if (it.children.isEmpty())
                result.add(it)
        }
        return result
    }

    fun forEachLevel(visit: TreeNodeVisitor<T>) {
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
        return result
    }

}

/**
 * @property path Mutable list of tree nodes
 * @param path Optional, default is empty list
 *
 *
 * */
open class TreePath<T>(val path: MutableList<TreeNode<T>> = mutableListOf()) {

    /**
     * @param node TreeNode<T>
     * adds node to the path
     * */
    fun addToPath(node: TreeNode<T>) {
        path.add(node)
    }

    /**
     * Stringifies the path
     * */
    fun getPathString(): String {
        return path.toString()
    }


    fun print() {
        path.forEach { print(it.value) }
        println()
    }

}
