package taught_by_mjlmj.stepone.set;

public interface Set<E> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    void traversal(Visitor<E> visitor);

    // Set没有索引的概念，因此需要提供遍历接口
    public static abstract class Visitor<E> {
        boolean stop;
        public abstract boolean visit(E element);
    }
}