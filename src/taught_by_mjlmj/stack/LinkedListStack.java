package taught_by_mjlmj.stack;


import taught_by_mjlmj.interfaces.List;
import taught_by_mjlmj.interfaces.Stack;
import taught_by_mjlmj.linked_list.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

    private final List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E element) {
        list.add(element);
    }

    @Override
    public E pop() {
        return list.remove(list.size() - 1);
    }

    @Override
    public E top() {
        return list.get(list.size() - 1);
    }

    @Override
    public void clear() {
        list.clear();
    }

}
