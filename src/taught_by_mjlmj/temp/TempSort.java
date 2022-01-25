package taught_by_mjlmj.temp;

import taught_by_mjlmj.tools.Asserts;
import taught_by_mjlmj.tools.Integers;

import java.util.Arrays;

public class TempSort {

    public static void bubbleSortBase(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin - 1] > array[begin]) {
                    swap(array, begin - 1, begin);
                }
            }
        }
        // --------------
        System.out.printf("%20s", "bubbleSortBase : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    public static void bubbleSortOpt1(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin - 1] > array[begin]) {
                    swap(array, begin - 1, begin);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
        // --------------
        System.out.printf("%20s", "bubbleSortOpt1 : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    public static void bubbleSortOpt2(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin - 1] > array[begin]) {
                    swap(array, begin - 1, begin);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
        // --------------
        System.out.printf("%20s", "bubbleSortOpt2 : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }


    public static void selectionSort(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        for (int end = array.length - 1; end > 0; end--) {
            int max = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] > array[max]) {
                    max = begin;
                }
            }
            swap(array, max, end);
        }
        // --------------
        System.out.printf("%20s", "selectionSort : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }


    public static void heapSort(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        int heapSize = array.length;
        for (int i = (array.length >> 1) - 1; i >= 0; i--) {
            siftDown(array, i, heapSize);
        }
        while (heapSize > 1) {
            swap(array, 0, heapSize - 1);
            heapSize--;
            siftDown(array, 0, heapSize);
        }
        // --------------
        System.out.printf("%20s", "heapSort : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    private static void siftDown(Integer[] array, int index, int heapSize) {
        Integer indexElement = array[index];
        int half = heapSize >> 1;
        while (index < half) {
            int leftChildIndex = (index << 1) + 1;
            Integer leftChildElement = array[leftChildIndex];
            int rightChildIndex = leftChildIndex + 1;
            if (rightChildIndex < heapSize) {
                Integer rightChildElement = array[rightChildIndex];
                if (rightChildElement > leftChildElement) {
                    leftChildElement = rightChildElement;
                    leftChildIndex = rightChildIndex;
                }
            }
            if (indexElement >= leftChildElement) break;
            array[index] = leftChildElement;
            index = leftChildIndex;
        }
        array[index] = indexElement;
    }


    public static void insertionSortBase(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        for (int begin = 1; begin < array.length; begin++) {
            int currentIndex = begin;
            while (currentIndex > 0 && array[currentIndex] < array[currentIndex - 1]) {
                swap(array, currentIndex, currentIndex - 1);
                currentIndex--;
            }
        }
        // --------------
        System.out.printf("%20s", "insertionSortBase : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    public static void insertionSortOpt1(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        for (int begin = 1; begin < array.length; begin++) {
            Integer currentElement = array[begin];
            int currentIndex = begin;
            while (currentIndex > 0 && currentElement < array[currentIndex - 1]) {
                array[currentIndex] = array[currentIndex - 1];
                currentIndex--;
            }
            array[currentIndex] = currentElement;
        }
        // --------------
        System.out.printf("%20s", "insertionSortOpt1 : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    public static void insertionSortOpt2(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        for (int begin = 1; begin < array.length; begin++) {
            Integer currentElement = array[begin];
            int currentIndex = begin;

            int b = 0;
            int e = begin;
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
        // --------------
        System.out.printf("%20s", "insertionSortOpt2 : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }


    public static void mergeSort(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        Integer[] leftArray = new Integer[array.length >> 1];
        mergeSort(leftArray, array, 0, array.length);
        // --------------
        System.out.printf("%20s", "mergeSort : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    private static void mergeSort(Integer[] leftArray, Integer[] array, int begin, int end) {
        if (end - begin < 2) return;
        int middle = (begin + end) >> 1;
        mergeSort(leftArray, array, begin, middle);
        mergeSort(leftArray, array, middle, end);
        merge(leftArray, array, begin, middle, end);
    }

    private static void merge(Integer[] leftArray, Integer[] array, int begin, int middle, int end) {
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


    public static void quickSort(Integer[] arr) {
        Integer[] array = Arrays.copyOf(arr, arr.length);
        // --------------
        quickSort(array, 0, array.length);
        // --------------
        System.out.printf("%20s", "quickSort : ");
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    private static void quickSort(Integer[] array, int begin, int end) {
        if (end - begin < 2) return;
        int pivotIndex = pivotIndex(array, begin, end);
        quickSort(array, begin, pivotIndex);
        quickSort(array, pivotIndex + 1, end);
    }

    private static int pivotIndex(Integer[] array, int begin, int end) {
        // [0, 1) * (end - begin)
        // [0, end - begin)
        // [begin, end)
        swap(array, begin, (int) (Math.random() * (end - begin) + begin));
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

    public static void main(String[] args) {
        Integer[] arr = Integers.random(20, 1, 20);
        System.out.printf("%20s", "original : ");
        Integers.println(arr);
        // --------------
        bubbleSortBase(arr);
        bubbleSortOpt1(arr);
        bubbleSortOpt2(arr);
        selectionSort(arr);
        heapSort(arr);
        insertionSortBase(arr);
        insertionSortOpt1(arr);
        insertionSortOpt2(arr);
        mergeSort(arr);
        quickSort(arr);
    }

    private static void swap(Integer[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

}
