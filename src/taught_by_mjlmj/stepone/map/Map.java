package taught_by_mjlmj.stepone.map;

public interface Map<K, V> {
    int size();
    boolean isEmpty();
    void clear();
    V put(K key, V value); // key之前存在，返回旧值；否则返回null
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    void traversal(Visitor<K, V> visitor);

    abstract class Visitor<K, V> {
        public boolean stop;
        public abstract boolean visit(K key, V value);
    }
}
