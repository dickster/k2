package com.wtw.demo.comparator

import java.util.*

class BarDiffer : Differ<Bar> {
    override fun diff(a: Bar?, b: Bar?): Optional<DiffResult> {
        println("comparing bars..." + a.toString() + " <=> " + b)
        return Optional.of(DiffResult.change(a, b))    }

}
