package com.wtw.demo.comparator;

import com.wtw.demo.comparator.annotations.Diffable;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AnnotatedDifferFactory implements DifferFactory {

    private Class clazz;

    private Map<String, Class<? extends Differ>> differsByClass = new HashMap();

    public AnnotatedDifferFactory(Class clazz) {
        this.clazz = clazz;
        // TODO :
        //  1: if class/type is annotated, but no fields then assume all fields.
        //  2: if some fields are annotated then only diff them. create differ based on their annotation values.
        //  3: log warning, no fields or class annotated.  default to including all fields.
        for (Field field : clazz.getDeclaredFields()) {
            Diffable diffable = field.getAnnotation(Diffable.class);
            if (diffable != null) {
                // at this point assume its never null.
                Class<? extends Differ> differClass = diffable.value();
                differsByClass.put(field.getName(), differClass);
            }
        }

    }



    @Override
    public Differ getDiffer(String fieldName) {
        Class<? extends Differ> aClass = differsByClass.get(fieldName);
        try {
            return aClass.newInstance();
        } catch (ReflectiveOperationException e) {
            return new ErrorDiffer("cant instantiate differ class " + differsByClass.get(fieldName) + " for " + fieldName);
        }
    }

}
