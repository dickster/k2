package com.wtw.demo.comparator;

public class AnnotatedDifferConfig implements DifferConfig {

    private final Reflector reflector;
    private DifferConfig parentConfig;
    private Class a, b;
    private DifferFactory differFactory;
    private Accessor accessor = new Accessor() {};

    public AnnotatedDifferConfig(DifferConfig parentConfig, Class<?> a, Class<?> b) {
        reflector = new JavaReflector(a, b);
        this.differFactory = new AnnotatedDifferFactory(a);
        this.parentConfig = parentConfig;
    }

    public Reflector getReflector() {
        return reflector;
    }

    @Override
    public DifferFactory getDifferFactory() {
        return differFactory;
    }

    @Override
    public Accessor getAccessor() {
        return accessor;
    }

    @Override
    public DifferConfig getParentConfig() {
        return parentConfig;
    }

}
