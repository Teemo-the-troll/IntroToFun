import java.util.*
import kotlin.math.abs

fun main() {
    Scanner(System.`in`).use { sc ->
        val input = sc.nextLine()
        if (!input.contains("AB") || !input.contains("BA")) {
            println("NO")
            return
        }
        val distanceABBA = input.indexOf("AB") - input.lastIndexOf("BA")
        val distanceBAAB = input.indexOf("BA") - input.lastIndexOf("AB")
        if (abs(distanceABBA) != 1 || abs(distanceBAAB) != 1)
            println("YES")
        else
            println("NO")
    }
}
