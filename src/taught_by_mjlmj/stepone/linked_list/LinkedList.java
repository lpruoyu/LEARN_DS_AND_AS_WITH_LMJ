package taught_by_mjlmj.stepone.linked_list;

import taught_by_mjlmj.stepone.interfaces.AbstractList;

public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(int index, E element) {
        if (null == element) {
            System.out.println("不能添加null元素");
            return;
        }
        rangeCheckForAdd(index);

        if (size == index) { // 往后加
            Node<E> node = new Node<>(last, element, null);
            if (last != null) {
                last.next = node;
            } else {
                first = node;
            }
            last = node;
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            if (null == prev) { // 在头部插入
                first = node;
            } else {
                prev.next = node;
            }
            next.prev = node;
        }

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null) { // 删除第0个元素
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) { // 删除最后一个元素
            last = prev;
        } else {
            next.prev = prev;
        }

        size--;

        return node.element;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E e = node.element;
        node.element = element;
        return e;
    }

    @Override
    public int indexOf(E element) {
        int i;
        Node<E> node = first;
        for (i = 0; i < size; i++) {
            if (node.element.equals(element)) break;
            node = node.next;
        }
        if (i == size) return ELEMENT_NOT_FOUND;
        return i;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node;

        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size:").append(size).append(" [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) sb.append(", ");
            sb.append(node);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (null == prev) {
                sb.append("null");
            } else {
                sb.append(prev.element);
            }

            sb.append("_").append(element).append("_");

            if (null == next) {
                sb.append("null");
            } else {
                sb.append(next.element);
            }

            return sb.toString();
        }

    }

}
