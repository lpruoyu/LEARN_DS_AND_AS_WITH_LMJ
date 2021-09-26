package taught_by_mjlmj.queue;

import taught_by_mjlmj.interfaces.Deque;
import taught_by_mjlmj.linked_list.LinkedList;

public class LinkedListDeque<E> implements Deque<E> {

    private final LinkedList<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    @Override
    public void enQueueRear(E element) {
        list.add(element);
    }

    @Override
    public E deQueueFront() {
        return list.remove(0);
    }

    @Override
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    @Override
    public E front() {
        return list.get(0);
    }

    @Override
    public E rear() {
        return list.get(list.size() - 1);
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
