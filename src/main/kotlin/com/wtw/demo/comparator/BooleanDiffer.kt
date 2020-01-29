package com.wtw.demo.comparator

import java.util.*

class BooleanDiffer : Differ<Boolean?> {
    override fun diff(a: Boolean?, b: Boolean?): Optional<DiffResult> {
        return if (a == b) Optional.of(DiffResult.change(a, b)) else Optional.empty()
    }
}
