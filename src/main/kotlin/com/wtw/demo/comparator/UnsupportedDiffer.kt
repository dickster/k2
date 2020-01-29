package com.wtw.demo.comparator

import java.util.*

class UnsupportedDiffer : Differ<Boolean> {
    override fun diff(a: Boolean?, b: Boolean?): Optional<DiffResult> {
        return Optional.of(DiffResult.error(a, b, "diff not supported"))
    }
}
