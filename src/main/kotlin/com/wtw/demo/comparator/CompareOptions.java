package com.wtw.demo.comparator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CompareOptions {

    boolean nullIsEmpty() default true;
    boolean ignore() default false;
    boolean stopOnDiff() default false;
    CompareViewType view() default CompareViewType.LEAF;

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

