package fiks

import com.sun.source.tree.Tree
import datastructures.TreeNode
import datastructures.TreePath
import java.io.File


/*
* t             -- pocet zadani
* n             -- pocet jazyku v zadani
* [n]           -- jazyky
* p             -- pocet prekladatelu
* [p] m c [m]   -- m -> pocet jazyku ktere umi, c -> cena, [m] -> struktura jazyku za sebou
* x y           -- z jazyku x do jazyka y
* */


fun main() {
    val lines = File("C:/Users/mikul/Downloads/input (1).txt").readLines().toTypedArray()
    /*val lines =
        File("C:\\Users\\mikul\\IdeaProjects\\BundledOps\\IntroToFun\\src\\main\\resources\\delniciTestInput.txt").readLines()
            .toTypedArray()*/
    val langList = mutableListOf<Language>()
    val outputFile = File("C:/Users/mikul/Downloads/output.txt")
    outputFile.createNewFile()
    val resultSet = mutableListOf<String>()
    var lineId = 1
    /**
     * input crunching to data
     * */
    while (lineId + 1 in 1..lines.size) {
        val listLanguages = mutableListOf<String>()
        val listTranslators = mutableListOf<Translator>()
        for (i in 1..lines[lineId].toInt()) { // foreach available language
            listLanguages.add(lines[lineId + i])
        }
        lineId += lines[lineId].toInt() + 1
        for (i in 1..lines[lineId].toInt()) { // foreach translator
            val rawTranslator = lines[lineId + i].split(" ")
            val langs = mutableListOf<String>()
            for (lang in 2 until rawTranslator.size)
                langs.add(rawTranslator[lang])
            listTranslators.add(Translator(rawTranslator[1].toInt(), langs))
        }
        lineId += lines[lineId].toInt() + 1
        val startofinish = lines[lineId].split(" ")
        langList.add(Language(listLanguages, listTranslators, startofinish[0], startofinish[1]))
        lineId += 1
    }
    /**
     * converting input to solved output
     * */
    for (translation in langList) {
        val start = TreeNode(Translator(0, listOf(translation.start)))
        val availableTranslators: MutableList<Translator> = mutableListOf<Translator>()
        availableTranslators.addAll(translation.translators)
        val currLevel = ArrayDeque<TreeNode<Translator>>()
        val nextLevel = ArrayDeque<TreeNode<Translator>>()
        val toRemove = mutableSetOf<Translator>()
        currLevel.add(start)
        // solving occurs here
        while (currLevel.size > 0) {
            val queuedNode = currLevel.removeFirst()
            val legitCandidates = availableTranslators.filter { t ->
                for (lang in t.knownLanguages) {
                    if (queuedNode.value.knownLanguages.contains(lang))
                        return@filter true
                }
                return@filter false
            }
            for (trans in legitCandidates) {
                val tempNode = TreeNode(trans)
                if (!queuedNode.value.knownLanguages.contains(translation.finish))
                    queuedNode.link(tempNode)
                nextLevel.add(tempNode)
                toRemove.add(trans)
            }
            if (currLevel.size == 0) {
                currLevel.addAll(nextLevel)
                nextLevel.clear()
                toRemove.forEach { availableTranslators.remove(it) }
            }
        }
        // end of solving region

        //output region
        /**
        To nas bude stat 7,-.   --> To nas bude stat ${path.sum},-.
        Pocet prekladu: 2.      --> Pocet prekladu: ${path.count}.
        Cestina                 --> path.forEach { print val }
        Nemcina
        Klingonstina
         * */
        val resultPaths = start.getPaths().filter {
            it.path.first().value.knownLanguages.contains(translation.finish)
        }
        var price = Integer.MAX_VALUE
        var bestPath: TreePath<Translator>? = null
        for (path in resultPaths) {
            var tempPrice = 0
            path.path.forEach { tempPrice += it.value.fee }
            if (tempPrice < price) {
                bestPath = path
                price = tempPrice
            }
        }
        if (bestPath == null) {
            resultSet.add("Takove prekladatele nemame.")
        } else {
            bestPath.path.reverse()
            resultSet.add("To nas bude stat ${price},-.")
            resultSet.add("Pocet prekladu: ${bestPath.path.size - 1}.")
            for (nodeId in 0..bestPath.path.size) {
                val translator = bestPath.path[nodeId].value
                val next = bestPath.path.getOrNull(nodeId + 1)
                if (next == null) {
                    resultSet.add(translation.finish)
                    break
                }

                for (lang in translator.knownLanguages) {
                    if(next.value.knownLanguages.contains(lang)) {
                        resultSet.add(lang)
                        break
                    }
                }
            }


        }
        //end of output region
    }
   // resultSet.forEach { println(it) }


    /*for (langNum in 1..amountOfLangs) { // for each language problem
        for (availableLang in 0 until lines[currentLanguageStart].toInt()) {

        }
        for ()



    }
*/





    outputFile.bufferedWriter().use { out ->
        resultSet.forEach {
            out.write(it)
            out.newLine()
        }
    }
}


fun TreePath<TreeNode<Translator>>.output() {
    val sum = this.path.sumOf { it.value.value.fee }
    println(sum)


}


class Language(
    val allLanguages: MutableList<String> = mutableListOf(),
    val translators: List<Translator>,
    var start: String,
    var finish: String
) {
    fun printAll() {
        printLangs()
        printTrans()
        printStartFinish()
    }

    fun printLangs() {
        println("Languages in problem:")
        for (lang in allLanguages)
            print("$lang ")
    }

    fun printTrans() {
        println("Translators:")
        this.translators.forEach { it.print() }
    }

    fun printStartFinish() {
        println("start: [${this.start}], finish: [${this.finish}]")
    }


}

class Translator(var fee: Int, val knownLanguages: List<String> = listOf()) {

    fun print() {
        printFee()
        printLangs()
    }

    fun printLangs() {
    //    print("Known Languages:")
        for (lang in knownLanguages)
            print("${lang.substring(0..3)} ")
//        println()
    }

    fun printFee() {
        println("Fee: ${fee}")
    }


}
