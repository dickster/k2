package com.wtw.demo.comparator.annotations;

import com.wtw.demo.comparator.Differ;
import com.wtw.demo.comparator.ObjectDiffer;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Diffable2 {

    Class<? extends Differ<?>> value() default ObjectDiffer.class;
    // use enum here instead.
    DifferCreationType type() default DifferCreationType.BY_CLASS;
    String name() default "";  // only used in conjunction with BEAN_NAME
}


