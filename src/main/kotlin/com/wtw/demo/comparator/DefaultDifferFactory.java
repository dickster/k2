package com.wtw.demo.comparator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class DefaultDifferFactory implements DifferFactory {

    private Class clazz;

    // note that this keeps the insertion order which is important because first match wins.
    private Map<Class, Class<? extends Differ>> differsByClass = new LinkedHashMap();


    public DefaultDifferFactory(Class clazz) {
        this.clazz = clazz;

        // do this in initializer somewhere else...  really this should be a configured Spring Bean.
        differsByClass.put(Byte.TYPE, NumberDiffer.class);
        differsByClass.put(Byte.class, NumberDiffer.class);
        differsByClass.put(Short.TYPE, NumberDiffer.class);
        differsByClass.put(Short.class, NumberDiffer.class);
        differsByClass.put(Float.TYPE, NumberDiffer.class);
        differsByClass.put(Float.class, NumberDiffer.class);
        differsByClass.put(Double.TYPE, NumberDiffer.class);
        differsByClass.put(Double.class, NumberDiffer.class);
        differsByClass.put(Integer.class, NumberDiffer.class);
        differsByClass.put(Integer.TYPE, NumberDiffer.class);
        differsByClass.put(Long.class, NumberDiffer.class);
        differsByClass.put(Long.TYPE, NumberDiffer.class);
        differsByClass.put(Number.class, NumberDiffer.class);
        differsByClass.put(Boolean.class, BooleanDiffer.class);
        differsByClass.put(Boolean.TYPE, BooleanDiffer.class);
        differsByClass.put(Date.class, UnsupportedDiffer.class);
        differsByClass.put(String.class, StringDiffer.class);
    }

    private Differ getDifferForClass(Class fieldType) throws IllegalAccessException, InstantiationException {
        Class<? extends Differ> differClass = differsByClass.get(fieldType);
        if (differClass == null) {
            differClass = differsByClass.keySet().stream().filter(c -> c.isAssignableFrom(fieldType)).findFirst().orElse(ObjectDiffer.class);
        }
        return differClass.newInstance();
    }

    @Override
    public Differ getDiffer(String fieldName) {
        //
        try {
            Field field = clazz.getDeclaredField(fieldName);
            Class<?> fieldType = field.getType();

            // what if object is an array???
            if (Collection.class.isAssignableFrom(fieldType)) {
                ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
                Class<?> itemType = (Class<?>) stringListType.getActualTypeArguments()[0];
                return new CollectionDiffer(getDifferForClass(itemType));
            }
            return getDifferForClass(fieldType);
        } catch (ReflectiveOperationException e) {
            // this will return TRUE and log msg.
            return new ErrorDiffer(e.getMessage());
        }
    }

}
