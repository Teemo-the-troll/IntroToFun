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
    for (i in elementPosition-2..elementPosition+2) {
        if (i < 0)
            output.add(0)
        else this.get(i)?.let { output.add(it) }
    }

     /*this.slice(indicies).map {
        it ?: 0
    }*/
    return output;

}
