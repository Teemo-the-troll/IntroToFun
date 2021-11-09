package edu

import java.util.*

fun main() {
    Scanner(System.`in`).use { sc ->
        val input = mutableListOf<Int>()
        while (sc.hasNextInt()) {
            input.add(sc.nextInt())
        }
        val avg = input.average()
        val filtered = input.filter { num -> num > avg }
        println(filtered.sum())
    }
}
