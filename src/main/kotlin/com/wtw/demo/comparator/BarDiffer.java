package com.wtw.demo.comparator;

import java.util.Optional;

public class BarDiffer implements Differ<Bar> {

    @Override
    public <S extends Bar> Optional<DiffResult> diff(S a, S b) {
        System.out.println("comparing bars..." + a + " <=> " + b);
        return Optional.of(DiffResult.change(a,b));
    }

}
