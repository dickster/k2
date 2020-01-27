package com.wtw.demo.comparator;


import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)

public @interface CompareView {
    CompareViewType value() default CompareViewType.LEAF;
}
