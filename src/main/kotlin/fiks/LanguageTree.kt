package fiks


typealias LangTreeVisitor<T> = (LanguageTree<T>) -> Unit

class LanguageTree<T>(var value: T, var fee: Int) {
    private val children = mutableListOf<LanguageTree<T>>()

    fun link(toLink: LanguageTree<T>) {
        toLink.children.add(this)
    }

    fun forEachDepthFirst(visit: LangTreeVisitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    fun forEachLevel(visit: LangTreeVisitor<T>) {
        visit(this)                                     // visits root
        val queue = ArrayDeque<LanguageTree<T>>()       // new queue?
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

    fun find(value: T): LanguageTree<T>? {
        var result: LanguageTree<T>? = null

        forEachDepthFirst {
            if (it.value == value) {
                result = it
            }
        }
        return result
    }
}

class LanguagePath<T>( val path: MutableList<LanguageTree<T>> = mutableListOf()) {

    fun addToPath(node: LanguageTree<T>) {
        path.add(node)
    }

    fun print() {
        path.forEach { print(it.value) }
    }
}


