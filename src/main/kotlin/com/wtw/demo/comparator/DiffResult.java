package com.wtw.demo.comparator;


import java.util.*;


public class DiffResult {

    private Object from;
    private Object to;
    private DiffType type;
    private String description;
    private Delegate delegate = new NonTerminalDelegate();


    public DiffResult(DiffType type, Object from, Object to) {
        // store this for later...need to know what the two objects are even if you dont have DIFFs
        this.from = from;
        this.to = to;
        this.type = type;
        forTerminalNode();
    }

    public DiffResult(Object from, Object to) {
        // store this for later...need to know what the two objects are even if you dont have DIFFs
        this.from = from;
        this.to = to;
        // assuming this is non-terminal.  you can override by calling forTerminalNode() later if you want.
        forNonTerminalNode();
    }

    public static DiffResult error(Object a, Object b, String msg) {
        return new DiffResult(DiffType.ERROR, a, b).withDescription(msg);
    }

    public static DiffResult change(Object a, Object b) {
        return new DiffResult(DiffType.CHANGE, a, b);
    }

    public static DiffResult deletion(Object a, Object b) {
        return new DiffResult(DiffType.DELETE, a, b);
    }

    public static DiffResult addition(Object a, Object b) {
        return new DiffResult(DiffType.ADD, a, b);
    }


    public DiffResult forNonTerminalNode() {
        delegate = new NonTerminalDelegate();
        return this;
    }

    public DiffResult forTerminalNode() {
        delegate = new TerminalDelegate();
        return this;
    }

    public int count() {
        return delegate.count();
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

    public Optional<DiffResult> get(String path) throws NoSuchFieldException {
        return delegate.get(path);
    }

    //   convenience method format() ::   list().format(a->a.toString())
    //                       format(lambda)   list().format(lambda);


    public void add(String name, DiffResult diffResult) {
        // should only do this when you have NOT explicitly given it a result.
        delegate.add(name, diffResult);
    }

    public DiffResult withDescription(String msg) {
        this.description = msg;
        return this;
    }

    @Override
    public String toString() {
        return "DiffResult{" +
                "type=" + type +
                ", from=" + from +
                ", to=" + to +
                ((description==null) ? "" : description) +
                '}';
    }

    interface Delegate {
        public List<DiffResult> list();

        public void add(String name, DiffResult diffResult);

        public Optional<DiffResult> get(String path) throws NoSuchFieldException;

        public int count();
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

        @Override
        public int count() {
            return 1;
        }
    }


    class NonTerminalDelegate implements Delegate {
        private Map<String, DiffResult> diffs = new HashMap<>();

        public List<DiffResult> list() {
            // the list can be empty.
            List<DiffResult> result = new ArrayList<DiffResult>();
            // check what type this is..single or object...
            for (String key : diffs.keySet()) {
                DiffResult diff = diffs.get(key);
                result.addAll(diff.list());
            }
            return result;
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

        @Override
        public int count() {
            // we have to do a deep count
            return diffs.values()
                    .stream()
                    .mapToInt(x -> x.count())
                    .sum();
        }

    }
}
