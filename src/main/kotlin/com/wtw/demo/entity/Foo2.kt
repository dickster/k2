package com.wtw.demo.entity

class Foo2(bar: Int) {
    private val x: String
    private val y: Any? = null
    private val z = 7

    init {
        x = bar.toString() + ""
    }
}
