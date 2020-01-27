package com.wtw.demo.comparator;

import java.util.Optional;

public interface PolicyComparator<T> {

    public default Optional<DiffResult> compare(String property, T a, T b) {
        return a==b ? null : Optional.of(new DiffResult(null, null, "blah"));
    }

}
