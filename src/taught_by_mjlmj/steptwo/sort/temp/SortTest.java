package taught_by_mjlmj.steptwo.sort.temp;

import taught_by_mjlmj.tools.Asserts;
import taught_by_mjlmj.tools.Integers;

public class SortTest {

    protected static int cmp(Integer v1, Integer v2) {
        return v1.compareTo(v2);
    }

    protected static void swap(Integer[] array, int i1, int i2) {
        Integer tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /* 冒泡排序 */
    public static void main(String[] args) {
        Integer[] array = Integers.random(1000000, 1, 201000000);
//        Integer[] array = Integers.random(10, 1, 15);
        Integers.println(array);
        quickSort(array);
//        mergeSort(array);
//        insertionSort3(array);
//        insertionSort2(array);
//        insertionSort1(array);
//        heapSort(array);
//        selectionSort(array);
//        bubbleSort(array);
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    private static void quickSort(Integer[] array) {
        quickSort(array, 0, array.length);
    }

    private static void quickSort(Integer[] array, int begin, int end) {
        if (end - begin < 2) return;
        int pivotIndex = pivot(array, begin, end);
        quickSort(array, begin, pivotIndex);
        quickSort(array, pivotIndex + 1, end);
    }

    private static int pivot(Integer[] array, int begin, int end) {
        int random = (int) (Math.random() * (end - begin) + begin);
        swap(array, random, begin);
        Integer pivotElement = array[begin];
        end--;
        while (begin < end) {
            while (begin < end) {
                if (array[end] > pivotElement) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin < end) {
                if (array[begin] < pivotElement) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        array[begin] = pivotElement;
        return begin;
    }

    static Integer[] leftArray;

    private static void mergeSort(Integer[] array) {
        leftArray = new Integer[array.length >> 1];
        mergeSort(array, 0, array.length);
    }

    private static void mergeSort(Integer[] array, int begin, int end) {
        if (end - begin < 2) return;
        int middle = (begin + end) >> 1;
        mergeSort(array, begin, middle);
        mergeSort(array, middle, end);
        merge(array, begin, middle, end);
    }

    private static void merge(Integer[] array, int begin, int middle, int end) {
        int li = 0, le = middle - begin;
        int ri = middle, re = end;
        int ai = begin;
        for (int i = 0; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        while (li < le) {
            if (ri < re && array[ri] < leftArray[li]) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }

    private static void insertionSort3(Integer[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            int currentIndex = begin;
            Integer currentElement = array[currentIndex];

            int b = 0, e = begin;
            while (b < e) {
                int m = (b + e) >> 1;
                if (currentElement < array[m]) {
                    e = m;
                } else {
                    b = m + 1;
                }
            }

            while (currentIndex > b) {
                array[currentIndex] = array[currentIndex - 1];
                currentIndex--;
            }
            array[b] = currentElement;
        }
    }

    private static void insertionSort2(Integer[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            int currentIndex = begin;
            Integer currentElement = array[currentIndex];
            while (currentIndex > 0 && cmp(currentElement, array[currentIndex - 1]) < 0) {
                array[currentIndex] = array[currentIndex - 1];
                currentIndex--;
            }
            array[currentIndex] = currentElement;
        }
    }

    private static void insertionSort1(Integer[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            int currentIndex = begin;
            while (currentIndex > 0 && cmp(array[currentIndex], array[currentIndex - 1]) < 0) {
                swap(array, currentIndex, currentIndex - 1);
                currentIndex--;
            }
        }
    }

    private static void heapSort(Integer[] array) {
        int arraySize = array.length;
        for (int i = (arraySize >> 1) - 1; i >= 0; i--) {
            siftDown(array, arraySize, i);
        }
        while (arraySize > 1) {
            swap(array, --arraySize, 0);
            siftDown(array, arraySize, 0);
        }
    }

    private static void siftDown(Integer[] array, int size, int index) {
        int half = size >> 1;
        Integer indexChild = array[index];
        while (index < half) {
            int leftIndex = (index << 1) + 1;
            Integer leftChild = array[leftIndex];
            int rightIndex = leftIndex + 1;
            if (rightIndex < size) {
                Integer rightChild = array[rightIndex];
                if (rightChild > leftChild) {
                    leftChild = rightChild;
                    leftIndex = rightIndex;
                }
            }
            if (cmp(indexChild, leftChild) >= 0) break;
            array[index] = leftChild;
            index = leftIndex;
        }
        array[index] = indexChild;
    }

    private static void selectionSort(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(array[begin], array[maxIndex]) > 0) {
                    maxIndex = begin;
                }
            }
            swap(array, maxIndex, end);
        }
    }

    private static void bubbleSort(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(array[begin - 1], array[begin]) > 0) {
                    swap(array, begin - 1, begin);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }

}
