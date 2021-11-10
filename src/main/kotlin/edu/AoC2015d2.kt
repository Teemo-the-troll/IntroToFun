package edu

import java.io.File

fun main() {
    val data = File("./src/main/resources/aocInput.txt").readLines()
    var totalSum = 0
    for (row in data) {
        val dimensions = row.split("x").map { it.toInt() }.sorted() //l w h
        val sides = arrayOf(
            2*dimensions[0]*dimensions[1],
            2*dimensions[1]*dimensions[2],
            2*dimensions[2]*dimensions[0]
        )
        totalSum += sides.sum() + sides[0]/2;
    }
    println(totalSum)

}
