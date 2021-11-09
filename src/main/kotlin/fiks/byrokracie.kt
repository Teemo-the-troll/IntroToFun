package fiks

import java.io.File
import java.io.FileReader
import kotlin.math.log

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
    val lines = File("./src/main/resources/random.txt").readLines().toTypedArray()
    val amountOfInputs = lines[0].toInt()
    val errorMessage = "ajajaj"

    var currPermitStart = 1
    for (lineNumber in 1..amountOfInputs) {
        val currPermitRules = lines[currPermitStart].split(" ").map { it.toInt() }
        val currPermitMap: MutableMap<String, MutableList<String>> = mutableMapOf()
        val currAmountOfPermits = currPermitRules[1]
        var acquiredPermits = mutableListOf<String>()
        if (currAmountOfPermits == 0) { // checks if i can go straight for the finish
            allRight(currPermitRules[2].toString())
            currPermitStart += 1 + currPermitRules[1]
            continue
        }

        for (permitLine in 1..currPermitRules[1]) { // prepares the map of needed stuff
            val currPermitLine = lines[permitLine + currPermitStart].split(" ")
            if (currPermitMap.containsKey(currPermitLine[0]))
                currPermitMap[currPermitLine[0]]!!.add(currPermitLine[1])
            else
                currPermitMap[currPermitLine[0]] = mutableListOf(currPermitLine[1])
        }

        if (!currPermitMap.containsKey(currPermitRules[2].toString())) {
            allRight(currPermitRules[2].toString())
            currPermitStart += 1 + currPermitRules[1]
            continue
        }
        for (permitNum in 0 until currPermitRules[0]) {
            if (!currPermitMap.keys.contains(permitNum.toString()))
                acquiredPermits.add(permitNum.toString())
        }

        /**
         * here the bs algorithm starts
         * */
        var changed = true
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
        }

        //if ()
        println(reverseSolution.reversed())
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


