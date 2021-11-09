import java.lang.Integer.MAX_VALUE
import java.lang.Integer.MIN_VALUE
import java.util.*

fun main() {
    println("Cisla:")
    val sc = Scanner(System.`in`)
    var min = MAX_VALUE
    var max = MIN_VALUE
    while (sc.hasNextInt()) {
        val input = sc.nextInt()
        max = max.coerceAtLeast(input)
        min = min.coerceAtMost(input)
    }
    println("max = $max")
    println("min = $min")


}

