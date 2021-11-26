package taught_by_mjlmj.stepone.queue;

import taught_by_mjlmj.stepone.heap.BinaryHeap;
import taught_by_mjlmj.stepone.interfaces.Queue;

import java.util.Comparator;

public class PriorityQueue<E> implements Queue<E> {

    private BinaryHeap<E> maxHeap;

    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<E> comparator) {
        maxHeap = new BinaryHeap<>(comparator);
    }

    @Override
    public int size() {
        return maxHeap.size();
    }

    @Override
    public void clear() {
        maxHeap.clear();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enQueue(E element) {
        maxHeap.add(element);
    }

    @Override
    public E deQueue() {
        return maxHeap.remove();
    }

    @Override
    public E front() {
        return maxHeap.get();
    }

}