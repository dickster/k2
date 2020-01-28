package com.wtw.demo.comparator;

public class DefaultDifferConfig implements DifferConfig {

    private final JavaReflector reflector;
    private DifferConfig parentConfig;
    private Class a,b;
    private Accessor accessor = new Accessor() {};
    private DifferFactory differFactory;

    public DefaultDifferConfig(DifferConfig parentConfig, Class a, Class b) {
        this.parentConfig = parentConfig;
        // these two classes should be the same (or at least extension of the other).
        this.a = a;
        this.b = b;

        // TODO : this should see if classes are...
        // 1: JSONObject.
        // 2: Annotated classes...class level or field level.
        // 3: different types of classes (WARN if not in same hierarchy, but its still allowed).
        this.parentConfig = parentConfig;
        this.reflector = new JavaReflector(a,b);
        this.differFactory = new DefaultDifferFactory(a);
    }

    // TODO : make this the java class reflector
    public Reflector getReflector() { return reflector; }

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
