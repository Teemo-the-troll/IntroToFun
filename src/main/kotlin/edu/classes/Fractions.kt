package edu.classes

import java.lang.ArithmeticException

class Fraction(private var numerator: Int, private var denominator: Int) {

    init {
        if (this.denominator == 0) {
            throw  ArithmeticException("Cannot divide by zero")
        }
    }


    fun minify(): Fraction {
        var maxDivisor = 1
        for (i in 1..this.denominator) {
            if (this.denominator % i == 0 && this.numerator % i == 0) {
                maxDivisor = i
            }
        }
        return Fraction(numerator / maxDivisor, denominator / maxDivisor)
    }

    fun toDouble(): Double {
        return this.numerator.toDouble() / this.denominator.toDouble()
    }

    private fun flip(): Fraction {
        return Fraction(this.denominator, this.numerator).minify()
    }

    operator fun plus(toAdd: Fraction): Fraction {
        return Fraction(
            (this.numerator * toAdd.denominator) + (toAdd.numerator * this.denominator),
            this.denominator * toAdd.denominator
        ).minify()
    }

    operator fun minus(toAdd: Fraction): Fraction {
        return Fraction(
            (this.numerator * toAdd.denominator) - (toAdd.numerator * this.denominator),
            this.denominator * toAdd.denominator
        ).minify()
    }

    operator fun times(toAdd: Fraction): Fraction {
        return Fraction(
            this.numerator * toAdd.numerator,
            this.denominator * toAdd.denominator
        ).minify()
    }

    operator fun div(toAdd: Fraction): Fraction {
        return this * toAdd.flip()
    }

    override operator fun equals(other: Any?): Boolean {
        return if (other != null) {
            if (other::class == this::class) {
                other.hashCode() == this.hashCode()
            } else
                false
        } else false
    }

    // I yet have to find a better way of doing this
    override fun hashCode(): Int {
        return this.numerator.hashCode() + this.denominator.hashCode()
    }

    fun print() {
        println("${this.numerator}/${this.denominator}")
    }


}
