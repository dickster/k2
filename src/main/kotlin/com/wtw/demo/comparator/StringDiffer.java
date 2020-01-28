package com.wtw.demo.comparator;

import java.util.Objects;
import java.util.Optional;

public class StringDiffer implements Differ<String> {

    private boolean ignoreCase = false;

    @Override
    public <S extends String> Optional<DiffResult> diff(S a, S b) {
        boolean comparison = Objects.equals(a,b);
        return comparison ?
                Optional.empty() :
                Optional.of(DiffResult.change(a, b));
    }
}
