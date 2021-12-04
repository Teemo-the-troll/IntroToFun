import datastructures.TreeNode
import datastructures.TreePath
import fiks.Language
import fiks.Translator
import java.io.BufferedWriter
import java.io.File

fun main() {
    /*val lines =
        File("C:\\Users\\mikul\\IdeaProjects\\BundledOps\\IntroToFun\\src\\main\\resources\\delniciTestInput.txt").readLines()
            .toTypedArray()*/
    val lines =
        File("C:/Users/mikul/Downloads/testinput.txt").readLines()
            .toTypedArray()
    val langList = mutableListOf<Language>()
    val outputFile = File("C:/Users/mikul/Downloads/playgroundOutput.txt")
    outputFile.createNewFile()
    val resultSet = mutableListOf<String>()
    var resultCounter = 0
    var failCounter = 0
    var okCounter = 0
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

    for (translation in langList) {
        val start = TreeNode(Translator(0, listOf(translation.start)))
        val availableTranslators: MutableList<Translator> = mutableListOf<Translator>()
        availableTranslators.addAll(translation.translators)
        val queue = ArrayDeque<TreeNode<Translator>>()
        queue.add(start)
        // solving occurs here
        while (queue.size > 0) {
            val queuedNode = queue.removeFirst()





        }
        val resPath = start.getPaths()

     /*   val resPath = start.getPaths().filter {
            it.path.first().value.knownLanguages.contains(translation.finish)
        }*/
        resultSet.add("Preklad c. ${langList.indexOf(translation)}: ${translation.start} -> ${translation.finish}")
        if (resPath.isEmpty()) {
            failCounter++
            resultSet.add("Takoveto prekladatele nemame")
        }
        else {
            okCounter++
            resultSet.addAll(resPath.prettyPrint(translation.finish))
        }
        resultCounter++

    }
    outputFile.bufferedWriter().use { out ->
        resultSet.forEach {
            out.write(it)
            out.newLine()
        }
    }
    //println(resultCounter)
}

fun List<TreePath<Translator>>.prettyPrint(finish: String, boolean: Boolean): Unit {
    this.forEach { treePath ->
        treePath.path.reverse()
        var fee = 0
        for (nodeId in 0..treePath.path.size) {
            val translator = treePath.path[nodeId].value
            fee += translator.fee
            val next = treePath.path.getOrNull(nodeId + 1)
            if (next == null) {
                print(" ")
                break
            }

            for (lang in translator.knownLanguages) {
                if(next.value.knownLanguages.contains(lang)) {
                    next.value.knownLanguages.forEach { print("$it ") }
                    print(" (${lang}) => ")
                    print("(${next.value.fee}) => ")
                    break
                }
            }
        }
        print(" $fee")
        println()
    }
}

fun List<TreePath<Translator>>.prettyPrint(finish: String): MutableList<String> {
    val result = mutableListOf<String>()
    this.forEach { treePath ->
        treePath.path.reverse()
        var string = ""
        var fee = 0
        for (nodeId in 0..treePath.path.size) {
            val translator = treePath.path[nodeId].value
            fee += translator.fee
            val next = treePath.path.getOrNull(nodeId + 1)
                ?: //string += (" $finish")
                break

            for (lang in translator.knownLanguages) {
                if(next.value.knownLanguages.contains(lang)) {
                    next.value.knownLanguages.forEach { string +=("$it ") }
                    string += (" (${lang}) => ")
                    string += ("(${next.value.fee}) => ")
                    break
                }
            }
        }
        string += (" $fee")
        result.add(string)
    }
    return result
}





