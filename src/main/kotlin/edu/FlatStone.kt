import java.util.*
import kotlin.math.ceil

import kotlin.math.roundToInt
import kotlin.math.roundToLong

fun main() {
    Scanner(System.`in`).use { sc ->
        val input = sc.nextLine().split(" ").map { i -> i.toDouble() }
        println((ceil(input[0] / input[2]) * ceil(input[1] / input[2])).roundToLong())
    }
}
