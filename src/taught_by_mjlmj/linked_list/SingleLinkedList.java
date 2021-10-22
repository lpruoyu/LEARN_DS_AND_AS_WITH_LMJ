package taught_by_mjlmj.linked_list;

import taught_by_mjlmj.interfaces.AbstractList;

public class SingleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    @Override
    public void add(int index, E element) {
        if (null == element) {
            System.out.println("不能添加null元素");
            return;
        }
        if (index == 0) {
            first = new Node<>(element, first);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = prev.next.next;
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
        size = 0;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        while (index != 0) {
            node = node.next;
            index--;
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
            sb.append(node.element);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

}
