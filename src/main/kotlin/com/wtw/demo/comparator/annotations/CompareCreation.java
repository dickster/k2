package com.wtw.demo.comparator.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CompareCreation {

    // specify class name, or bean name or bean interface, or factory bean/class name.
    // it should only be one of these mutually exclusive creation types.
    CompareCreationType value() default CompareCreationType.BEAN_CLASS;
    String name();

}
