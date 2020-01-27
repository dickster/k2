package com.wtw.demo.comparator;

import com.wtw.demo.entity.Policy;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.*;


// create a SETComparator & ListComparator.  (orderMatters for list)
public class CollectionComparator<T> {

    private Class<T> itemClass;


    // config = {
    //   orderMatters:boolean
    //   itemKey:'prop', or ['prop1', 'prop2',....]
    //   identityKey:  // means itemKey must exist.
    //   itemComparator

    public CollectionComparator(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    public Diff compare(Collection<T> a, Collection<T> b) {

        // get comparator ready...
        // getDiffer(itemClazz).withConfig()

        // iterate over collections and look for same object...
        // getfrom A---> find in B and remove both from set.

        // if config.orderMatters, assume it's a list. otherwise treat as a set.

        // compare(fromA, fromB0)
        // compare(fromA, fromB1)
        // compare(fromA, fromB2) etc...

        // how do I determine identity of each?  config to have a "key" function or array of properties.
        //  for example Vehicle key is VIN,  client is SIN, location is [number/street/city/country]
        // do I *need* key for each??     if no key given, assume all properties...i.e. use comparator to find.

        //  if key given,   fromA = a[?].    inB = findUsingKey(key(fromA),b)
        //  if (!inB) mark as deleted.
        //  else result = (compare(fromA, inB))
        //  if result==SAME, mark fromA & inB as being accounted for.   subsequent calls to find?? methods will ignore them.

        // if no key given fromA = a[?]      inB = findUsingComparator(fromA,b)

        // need ability to determine if elements...
        //    moved.
        //    deleted  (A has more elements than B)
        //    added (i.e. B has more elements than A)
        //    changed?

        return null;
    }

}
