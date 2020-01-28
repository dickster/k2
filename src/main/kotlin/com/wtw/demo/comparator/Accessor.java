package com.wtw.demo.comparator;

import java.lang.reflect.Field;

public interface Accessor {

    default Object getFieldValue(String fieldName, Object value) throws NoSuchFieldException {
        try {
            Field field = value.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(value);
        } catch (IllegalAccessException e) {
            throw new NoSuchFieldException(e.getMessage());
        }
    }


}
