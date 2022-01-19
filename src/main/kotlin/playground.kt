import datastructures.TreeNode
import datastructures.TreePath
import fiks.Language
import fiks.Translator
import java.io.BufferedWriter
import java.io.File
import java.util.*

fun main() {
    val testset = hashSetOf<test>()






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
