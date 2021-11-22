package edu.getters

import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val fleet = mutableListOf<Auto>()
    Scanner(System.`in`).use { sc ->
        println("input your fleet")
        val rawFleet = sc.nextLine()
        for (x in rawFleet.split(" ")) {
            fleet.add(Auto(x.toInt())) // do we need to care for "Int?" stuff?
        }
        while (true) {
            println("which car you want to (un)load and how much weight you want to transfer")
            val input = sc.nextLine()
            if (input == "end")
                break
            val (carId, weight) = input.split(" ").map { n -> n.toInt() }
            val car = fleet[carId - 1]
            car.addLoad(weight)
        }
    }

    println(
        "the average load of all cars in your fleet is ${
            fleet.map { car -> car.load }.average()
        }"
    )
}


class Auto(val capacity: Int) {
    var load = 0

    fun addLoad(weight: Int): Unit {
        if (this.load + weight < 0) {
            println("you cant create something out of nothing!")
        } else if (this.load + weight > this.capacity) {
            println("you are exceeding this cars max capacity (${this.capacity})")
        } else {
            println("load before ${this.load}")
            this.load += weight
            println("load after ${this.load}")
        }
    }

}

