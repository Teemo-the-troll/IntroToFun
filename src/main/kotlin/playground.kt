import java.util.Scanner

class Vector(coordintes: List<Int>) {

    var x1: Int = coordintes[0]
    var y1: Int = coordintes[1]
    var x2: Int = coordintes[2]
    var y2: Int = coordintes[3]

    init {
        normalize()
    }

    private fun normalize() {
        this.x2 = x2 - x1
        this.y2 = y2 - y1
        this.x1 = 0
        this.y1 = 0
    }


    fun addVector(toAdd: Vector): Vector {
        return Vector(listOf(0, 0, toAdd.x2 + x2, toAdd.y2 + y2))
    }

    fun multiplyByScalar(scalar: Int): Vector {
        return Vector(listOf(x1*scalar,y1*scalar,x2*scalar,y2*scalar))
    }

    fun scalarMultiply(vector: Vector): Int {
        return this.x1*vector.x1 + this.y1*vector.y1
    }

    fun print() {
        println("[$x1, $y1] [$x2, $y2]")
    }
}


fun main() {
    val sc = Scanner(System.`in`)
    println("vektor 1: x1,y1,x2,y2")
    val Vector1 = Vector(sc.nextLine().split(",").map { it.toInt() })
    println("vektor 2: x1,y1,x2,y2")
    val Vector2 = Vector(sc.nextLine().split(",").map { it.toInt() })

    Vector1.addVector(Vector2).print()
}



