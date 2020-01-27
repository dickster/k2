package com.wtw.demo.comparator;

public interface Accessor {

    default Object getFieldValue(String fieldName, Object value) throws NoSuchFieldException {
        try {
            return value.getClass().getField(fieldName).get(value);
        } catch (IllegalAccessException e) {
            throw new NoSuchFieldException(e.getMessage());
        }
    }


}
