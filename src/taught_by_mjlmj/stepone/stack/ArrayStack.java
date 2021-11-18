package taught_by_mjlmj.stepone.stack;

import taught_by_mjlmj.stepone.dynamic_array.ArrayList;
import taught_by_mjlmj.stepone.interfaces.List;
import taught_by_mjlmj.stepone.interfaces.Stack;

public class ArrayStack<E> implements Stack<E> {

    private final List<E> list = new ArrayList<>();

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
