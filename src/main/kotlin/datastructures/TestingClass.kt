package datastructures

fun main() {
    val testarraylist = ArrayListTest<Int>()
    testarraylist.add(8)
    testarraylist.add(156)
    testarraylist.add(7631)
    testarraylist.add(45)
    testarraylist.add(75)
    val array = testarraylist.toTypedArray()

    for (x in array)
        println(x)




}
/*

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
*/
