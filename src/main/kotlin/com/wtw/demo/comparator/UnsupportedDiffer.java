package com.wtw.demo.comparator;

import java.util.Objects;
import java.util.Optional;

public class UnsupportedDiffer implements Differ<Boolean> {

    @Override
    public <S extends Boolean> Optional<DiffResult> diff(S a, S b) {
        return Optional.of(DiffResult.error(a,b,"diff not supported"));
    }
}
