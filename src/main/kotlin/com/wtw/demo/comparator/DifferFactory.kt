package com.wtw.demo.comparator

interface DifferFactory {
    // TODO : factory will automatically turn old school java comparators into Differs.

    fun getDiffer(fieldName: String?): Differ<Any>?
}
