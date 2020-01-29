package com.wtw.demo.comparator

import java.util.*

class StringDiffer : Differ<String> {
    private val ignoreCase = false
    override fun diff(a: String?, b: String?): Optional<DiffResult> {
        val comparison = a == b
        return if (comparison) Optional.empty() else Optional.of(DiffResult.change(a, b))
    }
}
