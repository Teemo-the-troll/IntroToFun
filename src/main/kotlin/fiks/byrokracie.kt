package fiks

import java.io.BufferedWriter
import java.io.File

/*

6 6 0  => 6 povoleni, 6 radku zadani, chceme se dostat na 0      pujde to 4 5 2 3 1 0

1
1 0 0

pujde to 0 => neni zadne omezeni

2
6 6 0
0 1
1 2
1 3
3 2
2 4
2 5

pujde to 4 5 2 3 1 0
4 nepotrebuju nic, 5 taky ne, mam 4,5
mam 4 a 5 => muzu dostat 2, mam 4,5,2
muzu dostat 3 protoze na nej potrebuju 2, jeste nemuzu 1 protoze na ni potrebuju 3 mam 4,5,2,3
ted muzu teprve 1, mam 4,5,2,3,1
a muzu 0 protoze jsem potreboval 1 => mam 4,5,2,3,1,0
pocet odpovida zadani, pujde to



2 2 0       ajajaj => nemuzu si vyridit 1 kdyz nemam 0, ale nemuzu si vyridit 0 kdyz nemam 1
0 1
1 0

1 1 0       ajajaj => nemuzu se dostat na nulu kdyz nemam predtim vyrizenou nulu
0 0
*/
fun main() {
    val lines = File("C:/Users/mikul/Downloads/input.txt").readLines().toTypedArray()
    val outputFile = File("C:/Users/mikul/Downloads/output.txt")
    outputFile.createNewFile()
    val outputWriter = outputFile.bufferedWriter();
    val amountOfInputs = lines[0].toInt()
    val resultSet = mutableListOf<String>()

    var currPermitStart = 1
    for (lineNumber in 1..amountOfInputs) {
        val currPermitRules = lines[currPermitStart].split(" ").map { it.toInt() }
        val outputTree = PermitNode(currPermitRules[2])
        val currAmountOfPermits = currPermitRules[1]
        if (currAmountOfPermits == 0) { // checks if i can go straight for the finish
           // outputWriter.allRight(currPermitRules[2])
            resultSet.add(allRight(currPermitRules[2]))
            currPermitStart += 1 + currPermitRules[1]
            continue
        }
        var ok = true;
        for (permitLine in 1..currPermitRules[1]) { // prepares the map of needed stuff
            val currPermitLine = lines[permitLine + currPermitStart].split(" ").map { it.toInt() }
            val node = PermitNode(currPermitLine[1])
            val currPermit = outputTree.find(currPermitLine[0])
            if (currPermit != null) {
                if (!currPermit.ancestorExists(node.value))
                   currPermit.link(node)
                else {
                    ok = false;
                    //outputWriter.error()
                    //currPermitStart += 1 + currPermitRules[1]
                    resultSet.add(error())
                    break
                }
            }
        }
        if (ok)
            resultSet.add(allRight(outputTree.forEachLevel()))
            //outputWriter.allRight(outputTree.forEachLevel())
        currPermitStart += 1 + currPermitRules[1]


    }

    outputFile.bufferedWriter().use { out ->
        resultSet.forEach {
            out.write(it)
            out.newLine()
        }
    }


}



fun BufferedWriter.error() {
    this.append("ajajaj")
}
fun error(): String {
    return ("ajajaj")
}

fun allRight(value: Collection<Int>): String {
    val out = value.toString().replace("[\\[\\],]".toRegex(), "")
    return "pujde to $out"
}
fun allRight(value: Int): String {
    val out = value.toString().replace("[\\[\\],]".toRegex(), "")
    return "pujde to $out"
}
fun BufferedWriter.allRight(value: Collection<Int>) {
    val out = value.toString().replace("[\\[\\],]".toRegex(), "")
     this.append("pujde to $out")
}
fun BufferedWriter.allRight(value: String) {
     this.append("pujde to $value")
}
fun BufferedWriter.allRight(value: Int) {
     this.append("pujde to $value")
}




fun PermitNode<Int>.forEachLevel(): List<Int> {
    val output = sequence<Int> {
        yield(this@forEachLevel.value)                // visits root
        val queue = ArrayDeque<PermitNode<Int>>()       // new queue?
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


