package com.wtw.demo.comparator

import com.wtw.demo.comparator.annotations.Diffable
import java.util.*
import java.util.function.Consumer


class ObjectDiffer : Differ<Any> {

    private var config: DifferConfig? = null

    constructor() {}
    constructor(config: DifferConfig) {}

    private val fields: List<String?>
        private get() = config!!.reflector.fields

    private fun getDiffer(field: String?): Differ<Any> {
        // if config doesn't have it, ask parent.

        var differ: Differ<Any>? = config!!.differFactory.getDiffer(field)
        if (differ != null) {
            return differ
        }
        differ = config!!.parentConfig.differFactory.getDiffer(field)
        return differ ?: ErrorDiffer("cant find differ for field $field")
    }

    private fun getFieldValue(field: String?, value: Any?): Any? {
        return try {
            config!!.accessor.getFieldValue(field, value)
        } catch (e: NoSuchFieldException) {
            println("cant find field $field in object.  returning null ")
            null
        }
    }


    override fun diff(a: Any?, b: Any?): Optional<DiffResult> {

        maybeInitConfig(a, b)
        var result: DiffResult? = null
        for (field in fields!!) {

            // every diff has a top level  (type, from, to).  optionally has a list().

            val differ = getDiffer(field)
            val valueA = getFieldValue(field, a)
            val valueB = getFieldValue(field, b)
            println("   diffing " + a.toString() + " and " + b.toString())
            val diff = differ!!.diff(valueA, valueB)
            if (diff!!.isPresent) {
                if (result == null) {
                    result = DiffResult(a, b)
                }
                println("    difference - " + diff.get())
                result.add(field, diff.get())
            } else {
                println("    fields are the same.")
            }
        }
        println("result $result")
        return Optional.ofNullable(result)
    }

    private fun maybeInitConfig(a: Any?, b: Any?): DifferConfig? {
        // if config has already been set via constructor we'll leave it.
        // if not we'll take a look at object types and try to set a reasonable default config object.

        val diffable: Diffable? = a!!.javaClass.getAnnotation(Diffable::class.java)
        // or if any of the fields are annotated...

        if (diffable != null) {
            config = AnnotatedDifferConfig(config, a.javaClass, b!!.javaClass)
        }
        if (config == null) {
            config = DefaultDifferConfig(config, a.javaClass, b!!.javaClass)
        }
        return config
    }

}

fun main(args: Array<String>){


    val a = Foo()
    val b = Foo()
    val differ = ObjectDiffer()
    //ObjectDiffer comparator = new ObjectDiffer(config);


    val result = differ.diff(a, b)
    val diff = result!!.orElseThrow { IllegalStateException("there should be a diff") }
    val list: List<DiffResult?>? = diff!!.list()// returns List<Diff>,  but I need to save context for each...

    list!!.forEach(Consumer { d: DiffResult? -> println(d) })

//        diff.list(d -> d.toString());  // returns List<Diff>,  but I need to save context for each...


    diff.list()  // returns List<Diff>,  but I need to save context for each...

    //        diff.list(formatter, aggregatorFn);  // returns List<Diff>,  but I need to save context for each...

    // what do I get back?  a DiffResult

    // hmmm....I don't have the field name when i get the diff back.
    try {
        var d = diff.get("bar.z")
        println(d.get())
        d = diff.get("bar.x")
        println(d.get())
        d = diff.get("bar.y")
        println(d.get())
        d = diff.get("blah")
        if (d.isPresent()) {
            println("oops")
        } else {
            println("no diffs")
        }
        d = diff.get("nosuchfieldBlah")
        if (d.isPresent()) {
            println("oops")
        } else {
            println("no diffs")
        }
    } catch (e: NoSuchFieldException) {
        println("cant find field!!!")
    }
    //  diff.getFieldDiff("info", "locations");
    //diff.get(["info","locations"]);


}

