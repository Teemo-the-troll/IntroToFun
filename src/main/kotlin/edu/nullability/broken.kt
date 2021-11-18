package edu.nullability

import java.util.*
import kotlin.math.ceil

fun main() {
    Scanner(System.`in`).use() { it ->
        val input = mutableListOf<Int?>()
        while (it.hasNext()) {
            input.add(it.next().toIntOrNull())
        }
        val output: List<Int> = input.map { i ->
            i ?: ceil(input.getNeighbors(input.indexOf(i)).average()).toInt()
        }
        println(output.average())
    }
}

fun MutableList<Int?>.getNeighbors(elementPosition: Int): List<Int> {
    val output = mutableListOf<Int>()
    for (i in elementPosition-2..elementPosition+2) { // take 2 from left, 2 from right
        if (i != elementPosition) { // we don't want the element itself, idk better solution: martin hate pls
            if (i < 0 || i > this.size-1 || this[i] == null) // dodging the "indexOutOfBounds" shit
                output.add(0)
            else this[i]?.let { output.add(it) } // otherwise add the int value
        }
    }
    return output;
}
