package com.wtw.demo.comparator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaReflector implements Reflector {

    private final Class otherClass;
    private Class clazz;

    public JavaReflector(Class a, Class b) {
        this.clazz = a;
        this.otherClass = b;
        // TODO : i should make sure these classes line up and log warning if not.
    }

    @Override
    public List<String> getFields() {
        Function<Field,String> nameMapper = (f)->f.getName();
        return Arrays.stream(clazz.getDeclaredFields())
                .map(nameMapper)
                .collect(Collectors.toList());
    }
}
