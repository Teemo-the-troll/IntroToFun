package fiks

import algorithms.MergeSort
import java.io.File
import kotlin.math.absoluteValue
import kotlin.random.Random

fun main() {
    val input = File("C:/Users/mikul/Downloads/AlgorithmOutput.txt").readLines().toTypedArray()
    val inputAmount = input.size / 3
    val output = File("C:/Users/mikul/Downloads/klece.txt")
    val result = mutableListOf<String>()

    for (line in 0 until inputAmount) {
        val lineId = line * 3
        // mapping animals string to ordered animals list
        val animals = MergeSort<Animal>(Comparator { o1, o2 ->
            if (o1.voracity < o2.voracity)
                return@Comparator -1
            else if (o1.voracity == o2.voracity)
                return@Comparator 0
            return@Comparator 1
        }).mergeSort(input[lineId + 1].removeSuffix(" ").split(" ").mapIndexed { index, s -> Animal(s.toInt(), index) })



    }









    output.createNewFile()
    output.bufferedWriter().use { it ->
        for (line in result) {
            it.write(line)
            it.newLine()
        }
    }
}

class Animal(val voracity: Int, val id: Int)

fun generateInput(amount: Int, animalMaxVoracity: Int, maxVoracity: Int): List<String> {
    val result = mutableListOf<String>()
    for (x in 0..amount) {
        val animalCount = Random.nextInt(2000).absoluteValue
        result.add(animalCount.toString())
        var animals = ""
        for (num in 0..animalCount)
            animals += "${Random.nextInt(animalMaxVoracity).absoluteValue} "
        result.add(animals)
        result.add(Random.nextInt(maxVoracity).absoluteValue.toString())
    }
    return result
}
