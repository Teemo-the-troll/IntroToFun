import java.io.File
import java.io.FileReader
import java.util.*

fun main() {
    Scanner(FileReader("./src/main/resources/adventInput.txt")).use { sc ->
        val input = sc.nextLine()
        var result = 0
        input.chars().forEach { i ->
            if (i == '('.code)
                result++
            if (i == ')'.code)
                result--
        }
        println(result)
    }

      var result = 0;
       File("./src/main/resources/adventInput.txt").readText().chars().forEach { i ->
           if (i == '('.code)
               result++
           if (i == ')'.code)
               result--
       }
       println(result);

    var result2 = 0
    val input = File("./src/main/resources/adventInput.txt").readText()
    for (x in input) {
        if (x == '(')
            result2++
        if (x == ')')
            result2--
    }
    println(result2)

}
