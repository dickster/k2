package com.wtw.demo.comparator;

import java.util.Comparator;

public interface DifferFactory {
    // TODO : factory will automatically turn old school java comparators into Differs.

    public Differ getDiffer(String fieldName);
}

