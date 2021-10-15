import java.util.*

fun main() {
    Scanner(System.`in`).use { sc ->
        println("Input:")
        val input = sc.nextLine();
        var result = 0;
        for (c in input) {
            if (c.isDigit())
                result += c.digitToInt()
        }
        println(result)
    }
}
