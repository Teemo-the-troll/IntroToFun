package edu

import java.io.File
import java.util.*

fun main() {
    val input = File("./src/main/resources/Aoc2015D14.txt").readLines().map {
        val timer = 2503 // timer from the site
        val split = it.split(" ")
        /* [{name},"can","fly",
           {speed},"km/s","for",
           {endurance},"seconds,","but",
           "then","must","rest",
           "for",{rest time},"seconds"]*/
        val speed = split[3].toInt()
        val endurance = split[6].toInt()
        val recharge = split[13].toInt()
        var cycles = timer / (endurance + recharge) // how many times he gets to run and rest
        var distance = 0
        if (timer % (endurance + recharge) >= endurance) //checking for partial cycle (reindeer can run 12s but only has 7s on clock)
            cycles++
        else {
            distance += (timer % (endurance + recharge)) * speed // add those missing 7s to the distance traveled
        }
        distance += cycles * speed * endurance //calculate the distance traveled
        return@map distance
    }
    println(input.sorted()[0])
}
