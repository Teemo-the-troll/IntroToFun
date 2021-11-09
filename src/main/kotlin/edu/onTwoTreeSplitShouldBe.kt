import java.util.*

fun main() {
    Scanner(System.`in`).use { sc ->
        println("Cisla poprosim")
        var switch = false;
        var result = 0;
        while (sc.hasNextInt()) {
            val input = sc.nextInt();
            if (switch && input % 3 == 0){
                result += input
            }
            switch = !switch
        }
        println(result)
    }

}
