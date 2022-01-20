package edu

import java.io.File
import java.util.*

fun main() {
    val housesH1 = hashSetOf<House>()
    val housesH2 = hashSetOf<House>()
    var x = 0
    var y = 0
    val input = File("./src/main/resources/Aoc2015D3.txt").readLines()
    val chars = input[0].split("")
    val robosanta = Array<String>(chars.size/2) {""}
    val normalsanta = Array<String>(chars.size/2) {""}

    for (num in chars.indices) {
        if (num%2 == 0)
            robosanta[num/2] = chars[num]
        else
            normalsanta[num/2] = chars[num]
    }

    chars.forEach {
        when (it) {
            ">" -> x++
            "<" -> x--
            "^" -> y++
            "v" -> y--
        }
        housesH1.add(House(x,y))
    }

    x = 0
    y = 0

    robosanta.forEach {
        when (it) {
            ">" -> x++
            "<" -> x--
            "^" -> y++
            "v" -> y--
        }
        housesH2.add(House(x,y))
    }

    x = 0
    y = 0

    normalsanta.forEach {
        when (it) {
            ">" -> x++
            "<" -> x--
            "^" -> y++
            "v" -> y--
        }
        housesH2.add(House(x,y))
    }




    println("Number of unique houses part 1 ${housesH1.count()}")
    println("Number of unique houses part 2 ${housesH2.count()}")
}

class House(val x: Int, val y: Int) {

    override fun hashCode(): Int {
        return Objects.hash(x, y)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as House

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

}
