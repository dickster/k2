package com.wtw.demo.comparator

import java.util.*

class CollectionDiffer(private val itemDiffer: Differ<Any>) : Differ<Collection<Any>> {
    var identifier = "id"

    override fun diff(a: Collection<Any>?, b: Collection<Any>?): Optional<DiffResult> {

        val found: Map<Any?, Any?> = HashMap()
        for (valueFromA in a!!) {
            val valueFromB = findIn(b, valueFromA)  // need ID property.

            val diffResult = itemDiffer.diff(valueFromA, valueFromB)
            if (diffResult.isPresent) {
                val diff = diffResult.get()
            }
        }
        println("TODO: handle collections....")
        val result = DiffResult(a, b)
        // calculate the diffs here and add them...


        return if (result.count() == 0) Optional.empty() else Optional.of(result)

    }

    private fun findIn(b: Collection<*>?, valueFromA: Any): Any {
        return ""
    }

    fun withIdentifier(id: String): Differ<*> {
        identifier = id
        return this
    }

}
