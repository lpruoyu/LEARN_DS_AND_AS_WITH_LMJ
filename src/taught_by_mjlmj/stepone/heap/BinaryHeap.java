package taught_by_mjlmj.stepone.heap;

import taught_by_mjlmj.stepone.interfaces.AbstractHeap;
import taught_by_mjlmj.tools.printer.BinaryTreeInfo;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;

    public BinaryHeap() {
        this(null, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap(E[] elements) {
        this(elements, null);
    }

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (null == elements || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            int capacity = elements.length < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : elements.length;
            E[] data = (E[]) new Object[capacity];
            System.arraycopy(elements, 0, data, 0, elements.length);
            this.elements = data;
            this.size = elements.length;
            heapify();
        }
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity();
        elements[size] = element;
        siftUp(size);
        size++;
    }

    @Override
    public E remove() {
        ensureCapacity();
        E root = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        siftDown(0);
        return root;
    }

    @Override
    public E replace(E element) {
//        两个log(n) 不推荐
//        E root = remove();
//        add(element);
//        return root;
        elementNotNullCheck(element);
        final E root = get();
        elements[0] = element;
        siftDown(0);
        return root;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    private void heapify() {
        // 使用自下而上的siftDown
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftUp(int index) {
        E element = elements[index];
        while (index > 0) { // 保证有父节点
            int parentIndex = (index - 1) >> 1;
            E parentElement = elements[parentIndex];
            int cmp = compare(element, parentElement);
            if (cmp <= 0) break;
            elements[index] = parentElement;
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void siftDown(int index) {
        /*
        - 完全二叉树的非叶子节点个数为：floor( n / 2 )
        */
        E element = elements[index];
        int notLeafCount = size >> 1; // 非叶子节点个数
        while (index < notLeafCount) {
            /* 完全二叉树非叶子节点特性：
                    - 要么拥有左右子节点（度为2）
                    - 要么只有左子节点（度为1）
             */
            int maxChildIndex;
            E maxChildElement;
            int leftChildIndex = index + index + 1;
            int rightChildIndex = leftChildIndex + 1;
            E leftChildElement = elements[leftChildIndex];
            maxChildIndex = leftChildIndex;
            maxChildElement = leftChildElement;
            if (rightChildIndex < size) { // 存在右子节点
                E rightChildElement = elements[rightChildIndex];
                if (compare(rightChildElement, leftChildElement) > 0) {
                    maxChildIndex = rightChildIndex;
                    maxChildElement = rightChildElement;
                }
            }
            if (compare(element, maxChildElement) >= 0) break;
            elements[index] = maxChildElement;
            index = maxChildIndex;
        }
        elements[index] = element;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    private void emptyCheck() {
        if (size == 0) throw new IndexOutOfBoundsException("This heap is empty!");
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length + (elements.length >> 1);
            E[] newElements = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int nodeIndex = (int) node;
        int left = nodeIndex + nodeIndex + 1;
        return left >= size ? null : left;
    }

    @Override
    public Object right(Object node) {
        int nodeIndex = (int) node;
        int right = nodeIndex + nodeIndex + 2;
        return right >= size ? null : right;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }

}