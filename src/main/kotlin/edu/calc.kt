package edu

import java.util.*

open class Operation() {
     open fun calculate(number1: Int, number2: Int): Int {
         TODO()
     }
}

class PlusOperation(): Operation(){
    override fun calculate(number1: Int, number2: Int): Int {
        return number1 + number2;
    }
}

class TimesOperation(): Operation(){
    override fun calculate(number1: Int, number2: Int): Int {
        return number1 * number2;
    }
}

class MinusOperation(): Operation(){
    override fun calculate(number1: Int, number2: Int): Int {
        return number1 - number2;
    }
}

class DivisionOperation(): Operation(){
    override fun calculate(number1: Int, number2: Int): Int {
        if (number2 == 0) // tahle by mohla byt ok ne?
            throw Error("Cant divide by 0")
        return number1 / number2;
    }
}





fun main() {
    val operandi = mapOf<String, Operation>(
        Pair("+", PlusOperation()),
        Pair("-", MinusOperation()),
        Pair("*", TimesOperation()),
        Pair("/", DivisionOperation())
    )
    
    
    Scanner(System.`in`).use {
        println("Your input please")
        val input = it.nextLine()
        val split = input.split(" ")
        println(operandi[split[1]]?.calculate(split[0].toInt(), split[2].toInt()))
    }
    






}
