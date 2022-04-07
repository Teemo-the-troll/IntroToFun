package datastructures

import kotlin.jvm.Throws


@Suppress("UNCHECKED_CAST")
class ArrayListTest<T> : AbstractList<T>() {
    private var array: Array<Any?> = arrayOfNulls<Any>(10)
    private var realSize = 0


    override fun isEmpty(): Boolean {
     return realSize == 0
    }

    fun add(value: T): Boolean {
        if (realSize == array.size) {
            array = array.copyOf(array.size + 10)
        }
        array[realSize] = value
        return if (array[realSize] == value) {
            realSize++
            true
        } else false
    }

    @Throws(IndexOutOfBoundsException::class)
    override fun get(index: Int): T {
        if (checkIndex(index)) {
            throw IndexOutOfBoundsException()
        }
        return array[index] as T
    }

    override fun indexOf(element: T): Int {
        for (i in this.array.indices) {
            if (array[i]?.equals(element) == true) {
                return i
            }
        }
        return -1
    }

    override fun lastIndexOf(element: T): Int {
        for (i in this.array.indices.reversed()) {
            if (array[i]?.equals(element) == true) {
                return i
            }
        }
        return -1
    }

    override fun contains(element: T): Boolean {
        return indexOf(element) != -1
    }

    private fun checkIndex(index: Int): Boolean {
        return index in 0 until realSize
    }

    @Throws(IndexOutOfBoundsException::class)
    fun set(value: T, index: Int): Boolean {
        if (!checkIndex(index)) {
            throw IndexOutOfBoundsException()
        }
        array[index] = value
        return array[index] == value
    }

    override fun iterator(): Iterator<T> {
       return array.take(realSize).asSequence().map { it as T }.iterator()
    }

    override val size: Int
        get() = this.realSize
}
