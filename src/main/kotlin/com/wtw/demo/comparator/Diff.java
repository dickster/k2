package com.wtw.demo.comparator;


public class Diff implements DiffIF {

    // must be immutable.
    private DiffType type;
    private Object from;
    private Object to;
    private String context;

    // CAVEAT: you shouldn't really hang on to the actual objects you are comparing because that could
    // tie up a lot of memory.  just primitives or turn objects into a string is suggested.

    public Diff(DiffType type, Object from, Object to) {
        this.type = type;
        this.from = from!=null?from.toString():null;
        this.to = to!=null?to.toString():null;
    }

    public static Diff Change(Object from, Object to) {
        return new Diff(DiffType.CHANGE, from, to);
    }

    public static Diff None(Object from, Object to) {
        return new Diff(DiffType.NONE, from, to);
    }
    // these are only used in collections.
    public static Diff Add(Object from, Object to) {
        return new Diff(DiffType.ADD, from, to);
    }
    public static Diff Delete(Object from, Object to) {
        return new Diff(DiffType.DELETE, from, to);
    }
    // MOVE


    // for example parents will add "policy.location" so we know where the field came from.
    public Diff withContext(String context) {
        this.context = context;
        return this;
    }

    public String getName() {
        return name;
    }

    public DiffType getType() {
        return type;
    }

    public void setType(DiffType type) {
        this.type = type;
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }

    public String getContext() {
        return context;
    }

// e.g. "country", CHANGE, from: Canada, to: USA
    // e.g. "city", DELETE, from: London, to: <null>
    // e.g. "phoneNumber", ADD, from: <null>, to: 12348454

    // if you use "treatNullAsEmpty : false" then you'd get
    // e.g. "phoneNumber", CHANGE, from: <null>, to: 12348454
    // because it considers a null value as still valid/existing so it isn't an ADD.

}
