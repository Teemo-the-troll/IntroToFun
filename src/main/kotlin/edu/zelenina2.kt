package edu

import java.util.*

fun main() {
    println("zelenina:")
    val sc = Scanner(System.`in`)
    println("amount of vegetables")
    val zelenina = hashSetOf<String>().also {
        val amount = sc.nextInt()
        for (x in it.size..amount) {
            if (!it.add(sc.next()))
                println("duplicate or other error")
        }
    }
    println("amount of fruits")
    val fruits = hashSetOf<String>().also {
        val amount = sc.nextInt()
        for (x in it.size..amount) {
            if (!it.add(sc.next()))
                println("duplicate or other error")
        }
    }

    while (sc.hasNext()) {
        println("what you want to find?")
        val input = sc.next()
        if (zelenina.contains(input))
            println("vegetable")
        else if (fruits.contains(input))
            println("fruit")
        else println("other")
    }
}

