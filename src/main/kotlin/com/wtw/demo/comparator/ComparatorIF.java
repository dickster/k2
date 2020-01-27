package com.wtw.demo.comparator;

import java.util.Comparator;
import java.util.Optional;

public interface ComparatorIF {

    public  Optional<DiffIF> compare(Object a, Object b);

    // convert an old school java.comparator into a DiffIF generating one.
    public default ComparatorIF comparing(Comparator comparator) {
        return new ComparatorIF() {

            @Override
            public Optional<DiffIF> compare(Object a, Object b) {
                int result = comparator.compare(a,b);
                return result==0 ?
                        Optional.empty() :
                        Optional.of(new DiffIF() {

                            @Override
                            public int getComparison() {
                                return result;
                            }

                            @Override
                            public String getFrom() {
                                return a.toString();
                            }

                            @Override
                            public String getTo() {
                                return b.toString();
                            }

                            @Override
                            public String getDescription() {
                                return null;
                            }

                        });
            }
        };
    }

}
