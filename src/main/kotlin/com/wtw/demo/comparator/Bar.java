package com.wtw.demo.comparator;

public class Bar {

    private String x = "hello" + (Math.random()+"").substring(1,6);
    private Double y = Math.random()*100;
    private int z = (int) Math.floor(Math.random()*100);

    public Bar() {
    }

    public Bar(String x, Double y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
