package fiks

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

    // TODO langList is the data source from now on

    for (translation in langList) {


        
    }



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
        println("Known Languages:")
        for (lang in knownLanguages)
            print("$lang ")
    }
    fun printFee() {
        println("Fee: ${fee}")
    }





}
