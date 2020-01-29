package com.wtw.demo.comparator

import java.util.*

interface Differ<T> {
    fun diff(a: T?, b: T?): Optional<DiffResult>
}
