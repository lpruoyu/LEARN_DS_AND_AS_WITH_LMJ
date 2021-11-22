package taught_by_mjlmj.stepone.set;

import taught_by_mjlmj.stepone.interfaces.List;
import taught_by_mjlmj.stepone.linked_list.LinkedList;

public class ListSet<E> implements Set<E> {

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
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int index = list.indexOf(element);
        if (index == List.ELEMENT_NOT_FOUND) { // 找不到该元素，添加
            list.add(element);
        } else { // 可以找到说明该元素存在，可以替换该元素或者什么都不管
            list.set(index, element);
        }
    }

    @Override
    public void remove(E element) {
        if (list.indexOf(element) != List.ELEMENT_NOT_FOUND) {
            list.remove(element);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        for (int i = 0, size = list.size(); i < size; i++) {
            if (visitor.visit(list.get(i))) return;
        }
    }

}
