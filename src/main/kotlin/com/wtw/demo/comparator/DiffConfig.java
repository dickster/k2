package com.wtw.demo.comparator;

// the object comparator/container comparator needs this..

public interface DiffConfig {

    // TODO : make this the java class reflector
    public default Reflector getReflector() { return null; }

    public DifferFactory getDifferFactory();

    public Accessor getAccessor();
}




