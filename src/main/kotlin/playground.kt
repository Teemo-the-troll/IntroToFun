import java.io.File

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

        /**
         * prepares the map of needed stuff
         * */
        for (permitLine in 1..currPermitRules[1]) {
            val currPermitLine = lines[permitLine + currPermitStart].split(" ")
            if (currPermitMap.containsKey(currPermitLine[0]))
                currPermitMap[currPermitLine[0]]!!.add(currPermitLine[1])
            else
                currPermitMap[currPermitLine[0]] = mutableListOf(currPermitLine[1])
        }

        if (!currPermitMap.containsKey(currPermitRules[2].toString())) {
            fiks.allRight(currPermitRules[2].toString())
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
        if (reverseSolution.containsAll(acquiredPermits)) {
            println(reverseSolution.reversed())
        } else
            println(errorMessage)
        currPermitStart += 1 + currPermitRules[1]
    }
}

fun allRight(value: Collection<String>) {
    val out = value.toString().replace("[\\[\\],]".toRegex(), "")
    println(out)
}
