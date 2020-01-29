package com.wtw.demo.comparator;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class NumberDiffer implements Differ<Number> {

    private Double tolerance = 0.0;

    @NotNull
    @Override
    public Optional<DiffResult> diff(Number a, Number b) {
        double diff = Math.abs(a.doubleValue() - b.doubleValue());
        return diff > tolerance ?
                Optional.of(DiffResult.change(a, b)) :
                Optional.empty();
    }
}
