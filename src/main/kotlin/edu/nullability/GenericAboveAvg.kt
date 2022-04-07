package edu.nullability


fun <T : Number> List<T>.getNumsAboveAvg(): List<T> {
    val numClass = this[1].javaClass
    val avg = this.asSequence().map { it.toDouble() }.average()
    return filter { i -> i.toDouble() < avg }.map { i -> i }
}


fun main() {

}
