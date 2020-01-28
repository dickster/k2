package com.wtw.demo.comparator;

import java.util.Optional;

public class NumberDiffer implements Differ<Number> {

    private Double tolerance = 0.0;

    @Override
    public <S extends Number> Optional<DiffResult> diff(S a, S b) {
        double diff = Math.abs(a.doubleValue() - b.doubleValue());
        return diff > tolerance ?
                Optional.of(DiffResult.change(a, b)) :
                Optional.empty();
    }
}
