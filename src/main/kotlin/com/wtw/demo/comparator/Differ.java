package com.wtw.demo.comparator;

import java.util.Optional;

public interface Differ<T> {

    // this should probably be T extends
    public <S extends T> Optional<DiffResult> diff(S a, S b);

//    // convert an old school java.comparator into a DiffIF generating one.
//    public default Differ fromComparator(Comparator comparator) {
//        return new DiffResult() {
//
//        };
//    }

}
