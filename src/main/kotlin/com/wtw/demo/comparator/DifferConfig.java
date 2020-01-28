package com.wtw.demo.comparator;

public interface DifferConfig {

    public default Reflector getReflector() { return null; }

    public DifferFactory getDifferFactory();

    public Accessor getAccessor();

    public DifferConfig getParentConfig();

}




