package com.wtw.demo.comparator;

import java.util.List;

public interface DiffIF {

    public DiffType getType();
    public String getFrom();
    public String getTo();
    public int getComparison();
    public default String getDescription() { return null; };
    public default boolean hasChildren() { return false; }
    public default List<DiffResult> list() { throw new UnsupportedOperationException("default diffs dont have list");}
}


