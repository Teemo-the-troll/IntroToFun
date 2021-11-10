package edu

import java.lang.Math.pow
import java.util.*
import kotlin.math.pow

fun main() {
    Scanner(System.`in`).use {
        val input = it.nextInt()
        for (i in 0..input) {
            if (i.isStronk())
                println(i)
        }
    }
}

fun Int.isStronk(): Boolean {
    val digits = this.toString().split("(?<=.)".toRegex()).dropLast(1) // there was an annoying "" in the lsit
    val treated =    digits.map { i -> i.toInt() }
    var result = 0
    for (i in digits) {
        result += i.toDouble().pow(digits.size).toInt()
    }
    return this == result

}
