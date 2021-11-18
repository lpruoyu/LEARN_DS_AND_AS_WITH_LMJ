package taught_by_mjlmj.stepone.linked_list;

import taught_by_mjlmj.stepone.interfaces.AbstractList;

/*
    为了约瑟夫问题
    并且发挥出双向循环链表的最大威力
*/
public class CircleLinkedListPlus<E> extends AbstractList<E> {


    private Node<E> first;
    private Node<E> last;

    private Node<E> current;

    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    /**
     * 删除current，并且让current指向current.next
     * @return 被删除节点的element
     */
    public E remove() {
        if (current == null) return null;
        Node<E> next = current.next;
        E ele = remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }
        return ele;
    }

    private E remove(Node<E> node) {
        if (size == 1) {
            first = last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (first == node) { // 删除第0个元素
                first = next;
            }

            if (last == node) { // 删除最后一个元素
                last = prev;
            }
        }

        size--;

        return node.element;
    }

    @Override
    public void add(int index, E element) {
        if (null == element) {
            System.out.println("不能添加null元素");
            return;
        }
        rangeCheckForAdd(index);

        if (size == index) { // 往后加

            Node<E> node = new Node<>(last, element, first);
            if (last != null) {
                last.next = node;
                first.prev = node;
            } else {
                first = node;
                first.next = first;
                first.prev = first;
            }

            last = node;
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            prev.next = node;
            next.prev = node;
            if (index == 0) { // 在头部插入
                first = node;
            }
        }

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
//        Node<E> node = first;
//        if (size == 1) {
//            first = last = null;
//        } else {
//            node = node(index);
//            Node<E> prev = node.prev;
//            Node<E> next = node.next;
//            prev.next = next;
//            next.prev = prev;
//
//            if (index == 0) { // 删除第0个元素
//                first = next;
//            }
//            if (index == size - 1) { // 删除最后一个元素
//                last = prev;
//            }
//        }
//
//        size--;
//
//        return node.element;
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
