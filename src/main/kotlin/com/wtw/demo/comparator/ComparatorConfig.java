package com.wtw.demo.comparator;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// the object comparator/container comparator needs this..

public class ComparatorConfig {

    // need a factory that will read a properties file and create one of these...
    // can also reflect on a class hierarchy for @CompareOptions annotations and create one.

    //fields is a lambda   include(Field field) -> boolean

    private Map<String, Function<Class, Comparator>> types = new HashMap();

    private Function<Field, Boolean> filter = null;

    // if true, then comparator always returns true.  (no DIFFs).
    private boolean ignore = false;

    private boolean stopOnDiff = false;  // maybe for performance reasons you want to stop comparing
    // peers once one of them is different.

    // if true, (NULL -> "foo" is considered an addition;  if false, it's considered a change).
    // inversely,  TRUE: "foo" -> NULL is considered a deletion, else its considered a change
    private boolean treatNullAsEmpty = true;

    public ComparatorConfig() {

    }

    public List<String> getFields() {
        return null;
    }

    public ComparatorIF getComparator(String fieldName) {
        return null;
    }


//    public ComparatorConfig withTypes(Map<String, Function<Class, Comparator>> moreTypes) {
//        this.types.putAll(moreTypes);
//        return this;
//    }
//
//    public ComparatorConfig withType(String name, Function<Class, Comparator> lambda) {
//        this.types.put(name, lambda);
//        return this;
//    }
//
//    // convenience method for above...will look up the fields.
//    public List<String> getFieldNames() {
//        //return ["a", "b","c"];
//        return null;
//    }
//
//    public <T> ComparatorIF<T> getDiffer(Field field) {
//        Class<?> clazz = field.getDeclaringClass();
//        // default is based on type...you can add custom overrides here...
//    }
//

}
