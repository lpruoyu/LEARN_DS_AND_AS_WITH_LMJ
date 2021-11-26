package taught_by_mjlmj.stepone.set;

import taught_by_mjlmj.stepone.interfaces.Map;
import taught_by_mjlmj.stepone.interfaces.Set;
import taught_by_mjlmj.stepone.map.TreeMap;

import java.util.Comparator;

public class TreeSet2<E> implements Set<E> {

    private final Map<E, Object> map;
    private Comparator<E> comparator;

    public TreeSet2() {
        this(null);
    }

    public TreeSet2(Comparator<E> comparator) {
        this.comparator = comparator;
        map = new TreeMap<>(comparator);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        map.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}