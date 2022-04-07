package edu

class PrimesIterator(val maxValue: Int) : Iterator<Int?> {

    var i: Int = 2
    val encountered = mutableListOf<Int>()


    override fun hasNext(): Boolean {
        return i < maxValue
    }

    override fun next(): Int? {
        check(hasNext()) { "bool shiet" }
        for (x in encountered)
            if (i % x == 0)
                return null
        encountered.add(i)
        i++
        return i-1;
    }
}
