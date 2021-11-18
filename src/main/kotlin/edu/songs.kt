package edu

import java.util.*

fun main() {
    Scanner(System.`in`).use {
        println("number of songs?")
        val amountOfSongs = it.nextInt()
        val songList = SongList()
        for (i in 0 until amountOfSongs) {
            println("name of song")
            val name = it.next()
            println("interpret name")
            val interpret = it.next()
            println("song length")
            val length = it.nextInt()
            songList.data.add(Song(interpret, length, name))
        }
        while (true) {
            println(
                """
                3 options:
                1) average length of songs from an interpret
                2) amount of songs produced by an interpret
                3) search songs by phrase
                4) Exit
                """.trimIndent()
            )
            when (it.next().also { println("search term:") }) {
                "1" -> println(songList.avgInterpret(it.next()))
                "2" -> println(songList.countSongs(it.next()))
                "3" -> println(songList.findSongs(it.next()))
                "4" -> println("Exiting").also { return }
                else -> {
                    println("Unknown command")
                }
            }

        }
    }
}


class Song(val interpret: String, val length: Int, val name: String)
class SongList() {
    val data = mutableListOf<Song>()

    fun avgInterpret(musician: String): Float {
        val totalLength = this.filterByInterpret(musician).sumOf { song -> song.length };
        val count = this.countSongs(musician);
        return totalLength.div(count.toFloat());
    }

    private fun filterByInterpret(toFilterBy: String): List<Song> {
        return data.filter {
                song -> song.interpret == toFilterBy }
    }

    fun countSongs(interpret: String): Int {
        return this.filterByInterpret(interpret).count()
    }

    fun findSongs(phrase: String): List<String> {
        return data.filter { song ->
            song.name.contains(phrase)
        }.map { song -> song.name }

    }

}
