package taught_by_mjlmj.steptwo.sort.cmp;

import taught_by_mjlmj.steptwo.sort.Sort;

public class HeapSort<E extends Comparable<E>> extends Sort<E> {

    private int size;

    @Override
    protected void sort() {
        this.size = array.length;
        heapify();
        while (size > 1) {
            swap(0, size - 1);
            size--;
            siftDown(0);
        }
    }

    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index) {
        E element = array[index];
        int half = size >> 1;
        while (index < half) {
            int maxIndex;
            E maxChild;
            int leftChildIndex = index + index + 1;
            E leftChild = array[leftChildIndex];
            maxIndex = leftChildIndex;
            maxChild = leftChild;
            int rightChildIndex = leftChildIndex + 1;
            if (rightChildIndex < size) {
                E rightChild = array[rightChildIndex];
                if (cmp(leftChild, rightChild) < 0) {
                    maxIndex = rightChildIndex;
                    maxChild = rightChild;
                }
            }
            if (cmp(element, maxChild) >= 0) {
                break;
            }
            array[index] = maxChild;
            index = maxIndex;
        }
        array[index] = element;
    }

}
