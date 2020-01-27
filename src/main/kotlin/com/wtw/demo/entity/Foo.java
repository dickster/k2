package com.wtw.demo.entity;

public class Foo {

    private String x;
    private Object y;
    private final int z=7;

    public Foo(int bar) {
        this.x = bar+"";
    }
}


// KOTLIN =
// class Foo2(bar: Int) {
//    private val x: String
//    private val y: Any? = null
//    private val z = 7
//
//    init {
//        x = bar.toString() + ""
//        }
//  }
