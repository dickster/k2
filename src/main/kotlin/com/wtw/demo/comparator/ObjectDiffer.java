package com.wtw.demo.comparator;

import com.wtw.demo.entity.Policy;


import java.util.*;


public class ObjectDiffer implements Differ {

    private DiffConfig config;

    public ObjectDiffer() {

    }

    public ObjectDiffer(DiffConfig config) {

    }

    private List<String> getFields() {
        return config.getReflector().getFields();
    }

    private Differ getDiffer(String field) {
        return config.getDifferFactory().getDiffer(field);
    }

    private Object getFieldValue(String field, Object value) {
        try {
            return config.getAccessor().getFieldValue(field, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.out.println("cant find field " + field + " in object.  returning null ");
            // log this and return null... what should I do?
            return null;
        }
    }


    public Optional<DiffResult> diff(Object a, Object b) {

        maybeInitConfig(a,b);
        DiffResult result = null;
        for (String field : getFields()) {

            // every diff has a top level  (type, from, to).  optionally has a list().
            Differ differ = getDiffer(field);

            Object valueA = getFieldValue(field, a);
            Object valueB = getFieldValue(field, b);

            System.out.println("   diffing " + a.toString() + " and " + b.toString());

            Optional<DiffResult> diff = differ.diff(valueA, valueB);

            if (diff.isPresent()) {
                if (result==null) {
                    result = new DiffResult(a, b);
                }
                System.out.println("    difference - " + diff.get());
                result.add(field, diff.get());
            }
            else {
                System.out.println("    fields are the same.");
            }
        }
        System.out.println("result " + result);
        return Optional.ofNullable(result);
    }

    private void maybeInitConfig(Object a, Object b) {
        // if config has already been set via constructor we'll leave it.
        // if not we'll take a look at object types and try to set a reasonable default config object.
    }


    static void main(String... args) {
        Policy a = new Policy();
        a.setName("A");

        Policy b = new Policy();
        b.setName("B");

        // if the class has @Comparable annotation on it then reflect.   user can override config later.
        // if you have class level annotation and no fields, then it will include all.

        // will generate a default view.  assume this class isn't annotated.  if it is, uses Field annotations.

        // uses default config/types.  inspects all fields in object.  checks to make sure not primitive
        // assert(!clazz.isPrimitive())  when comparing, SHOULD also check to see if !JSONObject.
        ObjectDiffer differ = new ObjectDiffer();
        //ObjectDiffer comparator = new ObjectDiffer(config);

        // when you call compare, if there is no config specified it will  lazily create a default config and set it.
        //  Once config has been set (lazily or not) it cant be set again.

        Optional<DiffResult> result = differ.diff(a, b);

        DiffResult diff=result.orElseThrow(()->new IllegalStateException("there should be a diff"));

        // do something with results...
        //  should be able to see collapsed/aggregated levels.  e.g. only show vehicle Diff, not vehicle.vin, vehicle.year, etc...
        // when comaparator is saving Diff's, it must stuff in the name of the field for later purposes.
        diff.list();  // returns List<Diff>,  but I need to save context for each...

//        diff.list(d -> d.toString());  // returns List<Diff>,  but I need to save context for each...
        diff.list(/*viewconfig*/);  // returns List<Diff>,  but I need to save context for each...
        //        diff.list(formatter, aggregatorFn);  // returns List<Diff>,  but I need to save context for each...

        // what do I get back?  a DiffResult
        diff.get("vehicle.vin");
      //  diff.getFieldDiff("info", "locations");
        //diff.get(["info","locations"]);

    }


}
