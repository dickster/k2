package com.wtw.demo.comparator;

import com.wtw.demo.comparator.annotations.Diffable;
import com.wtw.demo.comparator.annotations.DifferCreationType;


@Diffable
public class Foo {

    @Diffable
    private Bar bar = new Bar();

    @Diffable(type=DifferCreationType.BY_CLASS, value=StringDiffer.class)
    private String blah = "blah";




    public Bar getBar() {
        return bar;
    }

    public String getBlah() {
        return blah;
    }

    public void setBlah(String blah) {
        this.blah = blah;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "bar=" + bar +
                ", blah='" + blah + '\'' +
                '}';
    }
}
