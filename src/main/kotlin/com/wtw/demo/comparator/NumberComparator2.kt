package com.wtw.demo.comparator

class NumberComparator2 {
    private val tolerance = 0.0
    fun compare(o1: Number, o2: Number): Boolean {
        val diff: Double = Math.abs(o1.toDouble() - o2.toDouble())
        return diff > tolerance
    }
}
