package com.wtw.demo.comparator;

import com.wtw.demo.entity.Policy;

import java.util.*;
import java.util.function.Function;

public class CollectionDiffer implements Differ<Collection> {

    // TODO : if the collection contains primitives then cant do much but put them both in a set and
    // compare.
    // if the collection

    private Differ itemDiffer;

    public CollectionDiffer(Differ itemDiffer) {
        this.itemDiffer = itemDiffer;
    }

    public String identifier = "id";

    @Override
    public <S extends Collection> Optional<DiffResult> diff(S a, S b) {
        // need an equality check on elements...if none, I'll just use the order they come out.

        // - iterate over A
        //   find equivalent in B.  (if exists).

        Map<Object,Object> found = new HashMap();

        for (Object valueFromA:a) {
            Object valueFromB = findIn(b, valueFromA);  // need ID property.
            Optional<DiffResult> diffResult = itemDiffer.diff(valueFromA, valueFromB);
            if (diffResult.isPresent()) {
                DiffResult diff = diffResult.get();
            }
        }

        DiffResult result = new DiffResult(a,b);
        return result.count()==0 ? Optional.empty() : Optional.of(result);
    }

    private Object findIn(Collection b, Object valueFromA) {
        return null;
    }

    public Differ withIdentifier(String id) {
        this.identifier = id;
        return this;
    }
}
