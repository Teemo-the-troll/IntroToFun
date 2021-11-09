import java.io.File
import java.util.*

fun main() {
    val lines = File("./src/main/resources/cnb.txt").readLines()
    val course = mutableMapOf<String, Float>()
    for (line in lines) {
        val parts = line.replace(",", ".").split("|")
        course[parts[3]] = parts[4].toFloat() / parts[2].toInt()
    }

    println("{POČET} {MĚNA 1} TO {MĚNA 2}")
    Scanner(System.`in`).use { sc ->
        while (sc.hasNextLine()) {
            val input = sc.nextLine().split(" ")
            if (!course.contains(input[1]) || !course.contains(input[3]))
                println("kod meny neexistuje")
            else {
                println(course.get(input[1])?.div(course.get(input[3])!!)?.times(input[0].toFloat()))
            }
        }
    }
}

