package taught_by_mjlmj.queue;

import taught_by_mjlmj.interfaces.Queue;

/*
    使用动态数组实现队列
    经过优化后底层是一个循环数组
*/

public class DynamicArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private int front;
    private E[] elements;

    public DynamicArrayQueue(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    public DynamicArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enQueue(E element) {
        ensureCapacity();
        elements[index(size)] = element;
        size++;
    }

    private int index(int n) {
        n += front;
        return n < elements.length ? n : (n - elements.length);
//        return (n + front) % elements.length;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            final int len = elements.length + (elements.length >> 1);
            E[] newElements = (E[]) new Object[len];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[index(i)];
            }

            elements = newElements;
            front = 0;
        }
    }

    @Override
    public E deQueue() {
        E e = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return e;
    }

    @Override
    public E front() {
        return elements[front];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }

        front = 0;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircleQueue { Capacity: ").append(elements.length).append(", ");
        sb.append("Size: ").append(size).append(", front: ").append(front).append(", elements: \n[");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) sb.append(", ");
            sb.append(elements[i]);
        }
        sb.append("]\n}");
        return sb.toString();
    }

}
