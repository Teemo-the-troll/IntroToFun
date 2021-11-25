import datastructures.TreeNode
import java.io.File

fun main() {
    val lines = File("./src/main/resources/random.txt").readLines().toTypedArray()
    val amountOfInputs = lines[0].toInt()
    val errorMessage = "ajajaj"

    var currPermitStart = 1
    for (lineNumber in 1..amountOfInputs) {
        val currPermitRules = lines[currPermitStart].split(" ").map { it.toInt() }
        val outputTree = TreeNode(currPermitRules[2])
        val currAmountOfPermits = currPermitRules[1]
        if (currAmountOfPermits == 0) { // checks if i can go straight for the finish
            allRight(currPermitRules[2].toString())
            currPermitStart += 1 + currPermitRules[1]
            continue
        }

        for (permitLine in 1..currPermitRules[1]) { // prepares the map of needed stuff
            val currPermitLine = lines[permitLine + currPermitStart].split(" ").map { it.toInt() }
            val node = TreeNode(currPermitLine[1])
            if (outputTree.find(currPermitLine[0]) != null) {
                outputTree.find(currPermitLine[0])!!.link(node)
            }
        }
        println(outputTree.forEachLevel())


        /*   var changed = true
           var reverseSolution = mutableListOf<String>(currPermitRules[2].toString())
           while (changed) {
               var temp = mutableListOf<String>()
               changed = false
               for (permit in reverseSolution.toList()) {
                   if (currPermitMap.containsKey(permit)) {
                       changed = true
                       for (subkey in currPermitMap[permit]!!) {
                           if (!reverseSolution.contains(subkey))
                               temp.add(subkey)
                       }
                       currPermitMap.remove(permit)
                   }
               }
               reverseSolution.addAll(temp.reversed())
           }*/

        //if ()
        // println(reverseSolution.reversed())
        currPermitStart += 1 + currPermitRules[1]


    }
}

fun allRight(value: String) {
    println("pujde to $value")
}

fun allRight(value: Collection<String>) {
    val out = value.toString().replace("[\\[\\],]".toRegex(), "")
    println("pujde to $out")
}

fun TreeNode<Int>.forEachLevel(): List<Int> {
    val output = sequence<Int> {
        yield(this@forEachLevel.value)                // visits root
        val queue = ArrayDeque<TreeNode<Int>>()       // new queue?
        children.forEach {
            queue.add(it)                        // add every child of root to queue
        }
        var node = queue.removeFirstOrNull()          // take first in queue
        while (node != null) {                        // until node is not null
            yield(node.value)                         // visit node
            node.children.forEach { queue.addFirst(it) } // for every node, enqueue its children
            node = queue.removeFirstOrNull()           // take first node of queue
        }
    }
    return output.toSet().reversed()
}





