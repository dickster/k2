package com.wtw.demo.comparator;

import java.util.Objects;
import java.util.Optional;

public class BooleanDiffer implements Differ<Boolean> {

    @Override
    public <S extends Boolean> Optional<DiffResult> diff(S a, S b) {
        return Objects.equals(a,b)  ?
                Optional.of(DiffResult.change(a, b)) :
                Optional.empty();
    }
}
