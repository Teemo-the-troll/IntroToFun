package edu

import java.util.*
import kotlin.math.ceil

fun main() {
    // val input = File("./src/main/resources/input.txt").readLines()
    Scanner(System.`in`).use { sc ->
        val banknotes = arrayOf(5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1)
        var input = sc.nextInt()
        var amount = 0
        for (banknote in banknotes) {
            while (banknote < input) {
                print("$banknote, ")
                input -= banknote
                amount++
            }
        }
        println("pocet bankovek je: $amount")
    }
}
