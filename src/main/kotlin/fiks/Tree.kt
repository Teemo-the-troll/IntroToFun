package fiks

import java.util.*


data class Tree<T>(val root: TreeNode<T>) {
    val levels = mutableListOf(mutableListOf(root))
    fun link() {

    }
}

data class TreeNode<T>(var value: T, var parent: TreeNode<T>?) {
    var depth = 0
    val children = mutableListOf<TreeNode<T>>()

    init {
        parent?.link(this)
    }

    fun link(toLink: TreeNode<T>) {
        toLink.parent = this
        toLink.depth = this.depth + 1
        this.children.add(toLink)
    }
}
