package com.wtw.demo.comparator;

import org.glassfish.jersey.internal.guava.Lists;

import java.util.*;
import java.util.function.Function;

public class CollectionComparatorConfig extends ComparatorConfig {

    private boolean orderMatters = false;
    private String[] keyProps = null;
    private boolean keyId = false;

    // config = {
    //   orderMatters:boolean
    //   itemKey:'prop', or ['prop1', 'prop2',....]
    //   identityKey:  // means itemKey must exist.
    //   itemComparator

    public CollectionComparatorConfig() {

    }

    public CollectionComparatorConfig withKeyProps(String... keys) {
        this.keyProps = keys;
        return this;
    }

    public CollectionComparatorConfig withKeyProp(String key) {
        this.keyProps = new String[]{key};
        return this;
    }

    public CollectionComparatorConfig withOrderMatters(boolean orderMatters) {
        this.orderMatters = orderMatters;
        return this;
    }

    public CollectionComparatorConfig withOrderMatters() {
        return withOrderMatters(true);
    }

    public CollectionComparatorConfig withKeyId() {
        // if true, for comparison it will only use the key props.
        // e.g. when comparing a collection of vehicles, it might make sense to use VIN as the key
        // that determines equality (ignore any other fields).
        //    withKey("VIN").withKeyId()
        return withOrderMatters(true);
    }

}
