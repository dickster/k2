package com.wtw.demo.comparator;

import java.util.Comparator;

public class StringComparator {


    // so a container comparator is a chain of comparables.  each one has to return (-1/0/1).  the caller
    // then has to store result -from/to/result/field

    // compare(a,b);
    // put(field, new Diff(result, field, a.toString(), b.toString())
    //
    // the problem is I need to know which ones are false...
    // if class type is comparable, then use comparator unless overriden by types
    // add a chain of lambdas which translate string...
    public int compare(String o1, String o2) {
        // TODO : handle add/delete and nulls.
        return  Comparator.comparing(String::toString).thenComparing(null)
    }
}
