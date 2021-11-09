import java.util.*

fun main() {
    println("zelenina:")
    val sc = Scanner(System.`in`)
    println("amount of vegetables")
    val zelenina = Array(sc.nextInt().also { println("input specific vegetables") }) {
        sc.next()
    }
    println("amount of fruits")
    val fruits = Array(sc.nextInt().also { println("input specific fruits") }) {
        sc.next()
    }

    while (sc.hasNext()) {
        println("what you want to find?")
        val input = sc.next()
        if (zelenina.contains(input))
            println("vegetable")
        else if (fruits.contains(input))
            println("fruit")
        else println("other")
    }
}
