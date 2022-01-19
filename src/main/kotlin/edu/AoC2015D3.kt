package edu

import java.io.File
import java.util.*

fun main() {
    val houses = hashSetOf<House>()
    var x = 0
    var y = 0
    val input = File("./src/main/resources/Aoc2015D3.txt").readLines()
    val chars = input[0].split("")
    chars.forEach {
        when (it) {
            ">" -> x++
            "<" -> x--
            "^" -> y++
            "v" -> y--
        }
        if (!houses.add(House(x, y))) {
            houses.filter { h -> h == House(x, y) }[0].timesVisited++
        }
    }
    println(houses.firstHalf())
}


fun HashSet<House>.firstHalf(): Int {
    return this.count()
}

class House(val x: Int, val y: Int) {
    var timesVisited = 1

    override fun hashCode(): Int {
        return Objects.hash(x, y)
    }

    override fun equals(other: Any?): Boolean {
        return other.hashCode() == this.hashCode()
    }
}
