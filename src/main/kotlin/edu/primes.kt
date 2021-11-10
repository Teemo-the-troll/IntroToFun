import java.util.*
import kotlin.math.*


fun main() {
    Scanner(System.`in`).use { sc ->
        println("Number N")
        val input = sc.nextDouble()
        for (number in 2..input.toInt()) {
            if (isPrime(number))
                println(number)
        }
    }
}

fun isPrime(number: Int): Boolean {
    var sumOfFactors = 1
    for (i in 2..floor(sqrt(number.toDouble())).toInt()) {
        if (number % i == 0)
            sumOfFactors += i + (number / i)
    }
    return (sumOfFactors == number)

}

