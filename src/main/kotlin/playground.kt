import datastructures.TreeNode
import datastructures.TreePath
import fiks.Language
import fiks.Translator
import java.io.BufferedWriter
import java.io.File
import java.util.*
import kotlin.math.abs

fun main() {
    val testset = hashSetOf<test>()

    for (x in 0..100)
        for (y in 0..100)
            testset.add(test(x,y))
    val intArray = Array(10000) { -1 }
    for ((index, value) in testset.withIndex())
        intArray[index] = value.hashCode()
    for (x in intArray)
        println(x)



}


class test(val x: Int, val y: Int ) {
    var timesVisited = 1

    override fun hashCode(): Int {
        return Objects.hash(x, y)
    }

    override fun equals(other: Any?): Boolean {
        return other.hashCode() == this.hashCode()
    }

}
