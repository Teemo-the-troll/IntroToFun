package edu

import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt

fun main() {
    Scanner(System.`in`).use {
        println("vase cislo prosim")
        val input = it.nextInt();
        for (n in 0..input) {
            if (n.isPerfect())
                println(n)
        }

    }
}

fun Int.isPerfect(): Boolean {
    var sumOfFactors = 1
    for (i in 2..floor(sqrt(this.toDouble())).toInt()) {
        if (this % i == 0)
            sumOfFactors += i + (this / i)
    }
    return (sumOfFactors == this)

}
