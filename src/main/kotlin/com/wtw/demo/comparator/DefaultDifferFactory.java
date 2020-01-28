package com.wtw.demo.comparator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultDifferFactory implements DifferFactory {

    private Class clazz;

    private Map<Class, Class<? extends Differ>> differsByClass = new HashMap();


    public DefaultDifferFactory(Class clazz) {
        this.clazz = clazz;

        // do this in initializer somewhere else...
        differsByClass.put(Number.class, NumberDiffer.class);
        differsByClass.put(String.class, StringDiffer.class);
    }


    private Differ getDifferForClass(Class fieldType) throws IllegalAccessException, InstantiationException {
        if (fieldType.isPrimitive()) {
            // handle - boolean, byte, char, short, int, long, float, and double.
            // used the boxed version of these...
            Class<? extends Differ> differClass = differsByClass.get(fieldType);
            return differClass.newInstance();
        }
        return new ObjectDiffer();
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
