package com.wtw.demo.comparator.annotations;

import com.wtw.demo.comparator.Differ;
import com.wtw.demo.comparator.ObjectDiffer;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Diffable {

    Class value() default ObjectDiffer.class;
    // use enum here instead.
    DifferCreationType type() default DifferCreationType.BY_CLASS;
    String name() default "";  // only used in conjunction with BEAN_NAME

}


// E.G.

// @Comparable   uses default comparator based on type.

// @Comparable(myComparator.class)   uses comparator of this class.

// @Comparable(mode=BEAN_NAME,name="comparatorBean")   uses comparator spring bean with given name

// @Comparable(mode=FACTORY_BEAN, name="comparatorFactory")   uses comparator spring bean with given name

// @Comparable(comparatorBean,Class, mode=BEAN_TYPE)   uses comparator of this class.

// @Comparable(comparatorBean,Class, mode=BEAN_TYPE, ignore=true, nullIsEmpty=false, stopOnDiff=true)

// @DiffView(DiffType.AGGREGATE)




// @CompareCreation("myComparator")
// private String foo

