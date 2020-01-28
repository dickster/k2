package com.wtw.demo.comparator;

import com.wtw.demo.comparator.annotations.Diffable;
import com.wtw.demo.entity.Policy;


import java.util.*;


public class ObjectDiffer implements Differ {

    private DifferConfig config;

    public ObjectDiffer() {

    }

    public ObjectDiffer(DifferConfig config) {

    }

    private List<String> getFields() {
        return config.getReflector().getFields();
    }

    private Differ getDiffer(String field) {
        // if config doesn't have it, ask parent.
        Differ differ = config.getDifferFactory().getDiffer(field);
        if (differ != null) {
            return differ;
        }
        differ = config.getParentConfig().getDifferFactory().getDiffer(field);

        return differ != null ? differ :
                new ErrorDiffer("cant find differ for field " + field);
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


    // TODO : this should throw exceptions...
    public Optional<DiffResult> diff(Object a, Object b) {

        maybeInitConfig(a, b);

        DiffResult result = null;
        for (String field : getFields()) {

            // every diff has a top level  (type, from, to).  optionally has a list().
            Differ differ = getDiffer(field);

            Object valueA = getFieldValue(field, a);
            Object valueB = getFieldValue(field, b);

            System.out.println("   diffing " + a.toString() + " and " + b.toString());

            Optional<DiffResult> diff = differ.diff(valueA, valueB);

            if (diff.isPresent()) {
                if (result == null) {
                    result = new DiffResult(a, b);
                }
                System.out.println("    difference - " + diff.get());
                result.add(field, diff.get());
            } else {
                System.out.println("    fields are the same.");
            }
        }
        System.out.println("result " + result);
        return Optional.ofNullable(result);
    }

    private DifferConfig maybeInitConfig(Object a, Object b) {
        // if config has already been set via constructor we'll leave it.
        // if not we'll take a look at object types and try to set a reasonable default config object.

        Diffable diffable = a.getClass().getAnnotation(Diffable.class);
        // or if any of the fields are annotated...
        if (diffable != null) {
            config = new AnnotatedDifferConfig(config, a.getClass(), b.getClass());
        }
        if (config == null) {
            config = new DefaultDifferConfig(config, a.getClass(), b.getClass());
        }
        return config;
    }


    public static void main(String... args) {
//        Policy a = new Policy();
//        a.setName("A");
//
//        Policy b = new Policy();
//        b.setName("B");

        Foo a = new Foo();

        Foo b = new Foo();

        ObjectDiffer differ = new ObjectDiffer();
        //ObjectDiffer comparator = new ObjectDiffer(config);

        Optional<DiffResult> result = differ.diff(a, b);

        DiffResult diff = result.orElseThrow(() -> new IllegalStateException("there should be a diff"));

        List<DiffResult> list = diff.list();// returns List<Diff>,  but I need to save context for each...
        list.forEach(d-> System.out.println(d));

//        diff.list(d -> d.toString());  // returns List<Diff>,  but I need to save context for each...
        diff.list(/*viewconfig*/);  // returns List<Diff>,  but I need to save context for each...
        //        diff.list(formatter, aggregatorFn);  // returns List<Diff>,  but I need to save context for each...

        // what do I get back?  a DiffResult
        try {
            diff.get("vehicle.vin");
        } catch (NoSuchFieldException e) {
            System.out.println("cant find field!!!");
        }
        //  diff.getFieldDiff("info", "locations");
        //diff.get(["info","locations"]);

    }


}
