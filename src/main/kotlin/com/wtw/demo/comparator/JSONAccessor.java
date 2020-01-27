package com.wtw.demo.comparator;


import org.json.JSONException;
import org.json.JSONObject;

class JSONAccessor {

    public Object getFieldValue(String fieldName, Object value) throws NoSuchFieldException {
        try {
            if (value instanceof JSONObject) {
                JSONObject json = (JSONObject) value;
                // i don't have any type info here...dont know if int/string/date/obj...???
                return json.get(fieldName);
            }
            throw new NoSuchFieldException("parent object is not JSONObject : cant find field " + fieldName);
        } catch (JSONException e) {
            throw new NoSuchFieldException(e.getMessage());
        }
    }
}
