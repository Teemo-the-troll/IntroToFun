package edu

import java.util.*
import java.util.function.IntPredicate
import kotlin.math.ceil

fun main() {
    Scanner(System.`in`).use { sc ->
        println("input all your numbers on one line, separated by spaces")
        // yes nextline with trailing whitespace is being annoying again, thats why the trim
        val input = sc.nextLine().trim().split(" ").map { i -> i.toInt() }.toList()
        println(
            """
            1) Vypsat všechna čísla dělitelná 11
            2) Vypsat všechna čísla, jejichž třetí mocnina končí na znak "3"
            3) Vypsat dvojnásobky všech čísel
            4) Vypsat 80 % z každého čísla zaokrouhlenou na nejbližší desítku nahoru
        """.trimIndent()
        )
        when (sc.nextInt()) {
            1 -> println(input.findAll { i -> i % 11 == 0 })
            // im not so crazy as to cast to string and back
            2 -> println(input.findAll { i -> i.pow(3) % 10 == 3 })
            3 -> println(input.changeAll { i -> i * 3 })
            // i assume you wanted ceil(), your assigment is erratic as always
            4 -> println(input.changeAll { i -> ceil(i * 0.8).toInt() })
            else -> println("Unknown command")
        }
    }
}

/**
 * fuck no did you see the method they have for pow()? also that one only accepts doubles
 * */
fun Int.pow(power: Int): Int {
    var output = this
    for (i in 1 until power)
        output *= this
    return output
}

fun List<Int>.findAll(predicate: IntPredicate): List<Int> {
    return this.filter { i -> predicate.test(i) }
}

fun List<Int>.changeAll(mutation: (Int) -> Int): List<Int> {
    return this.map { i -> mutation.invoke(i) }.toList()
}
