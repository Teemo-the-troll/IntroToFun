package edu

import java.util.*

fun main() {
    Scanner(System.`in`).use { sc ->
        var listOfNums = mutableListOf<Int>()
        println("input numbers, end the program typing end ")
        while (sc.hasNext()) {
            val input = sc.next()
            if (input.lowercase() == "end") {
                var unique = mutableListOf<Int>()
                for (num in listOfNums) {
                    if (listOfNums.lastIndexOf(num) == listOfNums.indexOf(num))
                        unique.add(num)
                }
                println("inputted nums")
                println(listOfNums.joinToString(","))
                println("uniques")
                println(unique.joinToString(","))
                return
            }
            listOfNums.add(input.toInt())
        }
    }
}
