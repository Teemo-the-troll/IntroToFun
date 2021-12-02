import fiks.LanguagePath
import fiks.LanguageTree

fun main() {
    val no1 = LanguageTree<Int>(1, 0)
    val no2 = LanguageTree<Int>(2, 78)
    val no3 = LanguageTree<Int>(3, 32)
    val no4 = LanguageTree<Int>(4, 10)
    val no5 = LanguageTree<Int>(5, 83)
    val path = LanguagePath<Int>()
    no1.link(no2)
    no1.link(no3)
    no3.link(no4)
    no3.link(no5)
    path.addToPath(no1)
    path.addToPath(no3)
    path.addToPath(no4)
  /*  no1.forEachDepthFirst { print(it.value) }
    println()
    no2.forEachDepthFirst { print(it.value) }
    println()
    no3.forEachDepthFirst { print(it.value) }
    println()

    no4.forEachDepthFirst { print(it.value) }
    println()

   */
    //no4.forEachDepthFirst { print(it.value) }
    //println()
    var totalValue = 0;
    path.path.forEach {
        totalValue += it.fee
        println("${it.value}  ${it.fee}" )
    }
    println(totalValue)



}






