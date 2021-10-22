package taught_by_mjlmj.dynamic_array;

import taught_by_mjlmj.interfaces.AbstractList;

public class ArrayList<E> extends AbstractList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] elementData;

    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elementData = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(int index, E element) {
        if (null == element) {
            System.out.println("不支持添加空元素");
            return;
        }
        rangeCheckForAdd(index);
        ensureArrayCapacity();
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
    }


    @Override
    public E remove(int index) {
        E e = get(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        elementData[size] = null;
        return e;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E e = elementData[index];
        elementData[index] = element;
        return e;
    }

    @Override
    public int indexOf(E element) {
        int i;
        for (i = 0; i < size; i++) {
            if (elementData[i].equals(element)) break;
        }
        if (i == size) {
            return ELEMENT_NOT_FOUND;
        }
        return i;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size:").append(size).append(" [");
        for (int i = 0; i < size; i++) {
            if (i != 0) sb.append(", ");
            sb.append(elementData[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    private void ensureArrayCapacity() {
        if (elementData.length == size) {
            int capacity = elementData.length + (elementData.length >> 1);
            E[] newElementData = (E[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newElementData[i] = elementData[i];
            }
            elementData = newElementData;
        }
    }

}
