package com.wtw.demo.comparator;


import org.glassfish.jersey.internal.guava.Lists;

import java.util.*;

// rename this to plain ole Diff

public class DiffResult {

    private Object from;
    private Object to;
    private DiffType type;

    private Delegate delegate;


    public DiffResult(DiffType type, Object from, Object to) {
        // store this for later...need to know what the two objects are even if you dont have DIFFs
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public DiffResult(Object from, Object to) {
        // store this for later...need to know what the two objects are even if you dont have DIFFs
        this.from = from;
        this.to = to;
    }

    public DiffResult forNonTerminalNode() {
        delegate = new NonTerminalDelegate();
        return this;
    }

    public DiffResult forTerminalNode() {
        delegate = new TerminalDelegate();
        return this;
    }

    public DiffType getType() {
        return this.type;
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }

    public List<DiffResult> list() {
        return delegate.list();
    }

    public Optional<DiffResult> get(String path) {
        return delegate.get(path);
    }

    //   convenience method format() ::   list().format(a->a.toString())
    //                       format(lambda)   list().format(lambda);


    public void add(String name, DiffResult diffResult) {
        // should only do this when you have NOT explicitly given it a result.
        delegate.add(name, diffResult);
    }


    interface Delegate {
        public List<DiffResult> list();
        public void add(String name, DiffResult diffResult);
        public Optional<DiffResult> get(String path) throws NoSuchFieldException;
    }

    class TerminalDelegate implements Delegate {
        public List<DiffResult> list() {
            // there is no children to flatten, just return this single instance.
            return Arrays.asList(DiffResult.this);
        }
        public void add(String name, DiffResult diffResult) {
            throw new UnsupportedOperationException("cant add to terminal diffResult");
        }
        public Optional<DiffResult> get(String path) throws NoSuchFieldException {
            throw new NoSuchFieldException("cant get from terminal diffResult");
        }
    }


    class NonTerminalDelegate implements Delegate {
        private Map<String, DiffResult> diffs = new HashMap<>();

        public List<DiffResult> list() {
            // use flatmap here...collection is either a DiffIF or a Map of DiffIFs.
            List<DiffResult> result = new ArrayList<DiffResult>();
            // check what type this is..single or object...
            for (String key : diffs.keySet()) {
                DiffResult diff = diffs.get(key);
                result.addAll(diff.list());
            }
            return null;
        }

        public void add(String name, DiffResult diffResult) {
            diffs.put(name, diffResult);
        }

        public Optional<DiffResult> get(String path) {
            //chop off first, get it and do get and rest of path.
            String[] split = path.split(".");
            // String first = blah
            // if diffs.get(first) then
            // D1 = diffs.get(first);
            //   return D1.get(restofPath)
            System.out.println("TODO: implement this get stuff....");
            return Optional.empty();
        }

    }
}
