package taught_by_mjlmj.queue;

import taught_by_mjlmj.interfaces.Queue;
import taught_by_mjlmj.linked_list.LinkedList;

public class LinkedListQueue<E> implements Queue<E> {

    private final LinkedList<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void enQueue(E element) {
        list.add(element);
    }

    @Override
    public E deQueue() {
        return list.remove(0);
    }

    @Override
    public E front() {
        return list.get(0);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
