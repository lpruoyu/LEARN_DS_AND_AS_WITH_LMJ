package taught_by_mjlmj.stepone.interfaces;

import java.util.Comparator;

public abstract class AbstractHeap<E> implements Heap<E> {

    protected int size;
    private Comparator<E> comparator;

    public AbstractHeap() {
    }

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    protected void elementNotNullCheck(E element) {
        if (null == element) throw new IllegalArgumentException("Element must not be null!");
    }

}
