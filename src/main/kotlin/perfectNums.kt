import java.util.*
import kotlin.math.*


fun main() {
    Scanner(System.`in`).use { sc ->
        println("Number N")
        val input = sc.nextDouble()
        for (number in 0..input.toInt()) {
            var i = 1
            var sumOfFactors = 0
            while (i <= floor(sqrt(number.toDouble()))) {
                if (number % i == 0)
                    sumOfFactors += i + (number / i)
                i++
            }
            if (sumOfFactors.toDouble() / 2 == number.toDouble())
                println(number)
        }
    }
}

