package taught_by_mjlmj.temp;

public class Temp {



    /*
public class MainReview {
//
//    public static void main(String[] args) {
//        Integer[] array = Integers.random(16, 1, 150);
//        System.out.print("original : ");
//        Integers.println(array);
//        bubbleSort(array);
//        selectionSort(array);
//        heapSort(array);
//        insertionSort(array);
//        mergeSort(array);
//        quickSort(array);
//        shellSort(array);
//
//        countingSort(array);
//        radixSort(array);
//
//    }
//
//    private static void radixSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        int max = array[0];
//        for (int i = 1; i < array.length; i++) {
//            if (array[i] > max) max = array[i];
//        }
//        int[] counts = new int[10];
//        Integer[] tempArray = new Integer[array.length];
//        for (int divider = 1; divider <= max; divider *= 10) {
//            radixSort(tempArray, counts, array, divider);
//        }
//        System.out.print("radixSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void radixSort(Integer[] tempArray, int[] counts, Integer[] array, int divider) {
//        for (int i = 0; i < counts.length; i++) {
//            counts[i] = 0;
//        }
//        for (int i = 0; i < array.length; i++) {
//            counts[array[i] / divider % 10]++;
//        }
//        for (int i = 1; i < counts.length; i++) {
//            counts[i] += counts[i - 1];
//        }
//        for (int i = array.length - 1; i >= 0; i--) {
//            tempArray[--counts[array[i] / divider % 10]] = array[i];
//        }
//        for (int i = 0; i < array.length; i++) {
//            array[i] = tempArray[i];
//        }
//    }
//
//    // 一定范围内的整数
//    private static void countingSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        int max = array[0];
//        int min = array[0];
//        for (int i = 1; i < array.length; i++) {
//            if (array[i] > max) max = array[i];
//            if (array[i] < min) min = array[i];
//        }
//        int[] counts = new int[max - min + 1];
//        for (int i = 0; i < array.length; i++) {
//            counts[array[i] - min]++;
//        }
//        for (int i = 1; i < counts.length; i++) {
//            counts[i] += counts[i - 1];
//        }
//        Integer[] tempArray = new Integer[array.length];
//        for (int i = array.length - 1; i >= 0; i--) {
//            tempArray[--counts[array[i] - min]] = array[i];
//        }
//        for (int i = 0; i < array.length; i++) {
//            array[i] = tempArray[i];
//        }
//        System.out.print("countingSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void shellSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        List<Integer> stepSequence = stepSequence(array.length);
//        for (Integer step : stepSequence) {
//            shellSort(array, step);
//        }
//        System.out.print("shellSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void shellSort(Integer[] array, Integer step) {
//        for (int col = 0; col < step; col++) {
//            for (int begin = col + step; begin < array.length; begin += step) {
//                int currentIndex = begin;
//                int currentElement = array[currentIndex];
//                while (currentIndex > col && currentElement < array[currentIndex - step]) {
//                    array[currentIndex] = array[currentIndex - step];
//                    currentIndex -= step;
//                }
//                array[currentIndex] = currentElement;
//            }
//        }
//    }
//
//    private static List<Integer> stepSequence(int step) {
//        List<Integer> stepSequence = new ArrayList<>();
//        while ((step >>= 1) > 0) {
//            stepSequence.add(step);
//        }
//        return stepSequence;
//    }
//
//    private static void quickSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        quickSort(array, 0, array.length);
//        System.out.print("quickSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void quickSort(Integer[] array, int begin, int end) {
//        if (end - begin < 2) return;
//        int pivotIndex = pivotIndex(array, begin, end);
//        quickSort(array, begin, pivotIndex);
//        quickSort(array, pivotIndex + 1, end);
//    }
//
//    private static int pivotIndex(Integer[] array, int begin, int end) {
//        swap(array, begin, begin + (int) (Math.random() * (end - begin)));
//        Integer pivotElement = array[begin];
//        end--;
//        while (begin < end) {
//            while (begin < end) {
//                if (array[end] > pivotElement) {
//                    end--;
//                } else {
//                    array[begin++] = array[end];
//                    break;
//                }
//            }
//            while (begin < end) {
//                if (array[begin] < pivotElement) {
//                    begin++;
//                } else {
//                    array[end--] = array[begin];
//                    break;
//                }
//            }
//        }
//        array[begin] = pivotElement;
//        return begin;
//    }
//
//    private static void mergeSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        Integer[] leftArray = new Integer[array.length >> 1];
//        mergeSort(leftArray, array, 0, array.length);
//        System.out.print("mergeSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void mergeSort(Integer[] leftArray, Integer[] array, int begin, int end) {
//        if (end - begin < 2) return;
//        int middle = (begin + end) >> 1;
//        mergeSort(leftArray, array, begin, middle);
//        mergeSort(leftArray, array, middle, end);
//        merge(leftArray, array, begin, middle, end);
//    }
//
//    private static void merge(Integer[] leftArray, Integer[] array, int begin, int middle, int end) {
//        int li = 0, le = middle - begin;
//        int ri = middle, re = end;
//        int ai = begin;
//        for (int i = 0; i < le; i++) {
//            leftArray[i] = array[begin + i];
//        }
//        while (li < le) {
//            if (ri < re && array[ri] < leftArray[li]) {
//                array[ai++] = array[ri++];
//            } else {
//                array[ai++] = leftArray[li++];
//            }
//        }
//    }
//
//    private static void insertionSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int begin = 1; begin < array.length; begin++) {
//            int currentIndex = begin;
//            Integer currentElement = array[begin];
//            int b = 0;
//            int e = begin;
//            while (b < e) {
//                int m = (b + e) >> 1;
//                if (currentElement < array[m]) {
//                    e = m;
//                } else {
//                    b = m + 1;
//                }
//            }
//            while (currentIndex > b) {
//                array[currentIndex] = array[currentIndex - 1];
//                currentIndex--;
//            }
//            array[b] = currentElement;
//        }
//        System.out.print("insertionSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void heapSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        int heapSize = array.length;
//        for (int i = (array.length >> 1) - 1; i >= 0; i--) {
//            siftDown(array, heapSize, i);
//        }
//        while (heapSize > 1) {
//            swap(array, 0, --heapSize);
//            siftDown(array, heapSize, 0);
//        }
//        System.out.print("heapSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void siftDown(Integer[] array, int heapSize, int index) {
//        Integer currentElement = array[index];
//        int half = heapSize >> 1;
//        while (index < half) {
//            int leftChildIndex = (index << 1) + 1;
//            int rightChildIndex = leftChildIndex + 1;
//            Integer leftChildElement = array[leftChildIndex];
//            if (rightChildIndex < heapSize) {
//                Integer rightChildElement = array[rightChildIndex];
//                if (leftChildElement < rightChildElement) {
//                    leftChildElement = rightChildElement;
//                    leftChildIndex = rightChildIndex;
//                }
//            }
//            if (currentElement >= leftChildElement) break;
//            array[index] = leftChildElement;
//            index = leftChildIndex;
//        }
//        array[index] = currentElement;
//    }
//
//    private static void selectionSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int end = array.length - 1; end > 0; end--) {
//            int max = 0;
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin] > array[max]) {
//                    max = begin;
//                }
//            }
//            swap(array, max, end);
//        }
//        System.out.print("selectionSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void bubbleSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int end = array.length - 1; end > 0; end--) {
//            int sortedIndex = 1;
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin - 1] > array[begin]) {
//                    swap(array, begin - 1, begin);
//                    sortedIndex = begin;
//                }
//            }
//            end = sortedIndex;
//        }
//        System.out.print("bubbleSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void swap(Integer[] array, int i1, int i2) {
//        Integer temp = array[i1];
//        array[i1] = array[i2];
//        array[i2] = temp;
//    }
//


//
//    public static void main(String[] args) {
//        Integer[] array = Integers.random(10, 1, 100);
//        System.out.print("original : ");
//        Integers.println(array);
//        bubbleSort1(array);
//        bubbleSort2(array);
//        bubbleSort3(array);
//        selectionSort(array);
//        heapSort(array);
//        insertionSort1(array);
//        insertionSort2(array);
//        insertionSort3(array);
//        mergeSort(array);
//        quickSort(array);
//        shellSort(array);
//
//        countingSort(array);
//        radixSort(array);
//
//    }
//
//    private static void radixSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        int max = array[0];
//        for (int i = 1; i < array.length; i++) {
//            if (array[i] > max) {
//                max = array[i];
//            }
//        }
//        int[] counts = new int[10];
//        int[] tempArray = new int[array.length];
//        for (int divider = 1; divider <= max; divider *= 10) {
//            radixSort(array, counts, tempArray, divider);
//        }
//        System.out.print("radixSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void radixSort(Integer[] array, int[] counts, int[] tempArray, int divider) {
//        for (int i = 0; i < counts.length; i++) {
//            counts[i] = 0;
//        }
//        for (int i = 0; i < array.length; i++) {
//            counts[array[i] / divider % 10]++;
//        }
//        for (int i = 1; i < counts.length; i++) {
//            counts[i] += counts[i - 1];
//        }
//        for (int i = array.length - 1; i >= 0; i--) {
//            tempArray[--counts[array[i] / divider % 10]] = array[i];
//        }
//        for (int i = 0; i < array.length; i++) {
//            array[i] = tempArray[i];
//        }
//    }
//
//    private static void countingSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        int max = array[0];
//        int min = array[0];
//        for (int i = 1; i < array.length; i++) {
//            if (array[i] > max) {
//                max = array[i];
//            }
//            if (array[i] < min) {
//                min = array[i];
//            }
//        }
//        int[] counts = new int[max - min + 1];
//        for (int i = 0; i < array.length; i++) {
//            counts[array[i] - min]++;
//        }
//        for (int i = 1; i < counts.length; i++) {
//            counts[i] += counts[i - 1];
//        }
//        Integer[] tempArray = new Integer[array.length];
//        for (int i = array.length - 1; i >= 0; i--) {
//            tempArray[--counts[array[i] - min]] = array[i];
//        }
//        for (int i = 0; i < array.length; i++) {
//            array[i] = tempArray[i];
//        }
//        System.out.print("countingSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void shellSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//
//        List<Integer> stepSequence = stepSequence(array);
//        for (Integer step : stepSequence) {
//            shellSort(array, step);
//        }
//
//        System.out.print("shellSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void shellSort(Integer[] array, Integer step) {
//        for (int col = 0; col < step; col++) {
//            // 对每一列进行插入排序
//            for (int begin = col + step; begin < array.length; begin += step) {
//                int currentIndex = begin;
//                int currentElement = array[currentIndex];
//                while (currentIndex > col && currentElement < array[currentIndex - step]) {
//                    array[currentIndex] = array[currentIndex - step];
//                    currentIndex -= step;
//                }
//                array[currentIndex] = currentElement;
//            }
//        }
//    }
//
//    private static List<Integer> stepSequence(Integer[] array) {
//        List<Integer> stepSequence = new ArrayList<>();
//        int step = array.length;
//        while ((step >>= 1) > 0) {
//            stepSequence.add(step);
//        }
//        return stepSequence;
//    }
//
//    private static void quickSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        quickSort(array, 0, array.length);
//        System.out.print("quickSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void quickSort(Integer[] array, int begin, int end) {
//        if (end - begin < 2) return;
//
//        int pivotIndex = pivotIndex(array, begin, end);
//        quickSort(array, begin, pivotIndex);
//        quickSort(array, pivotIndex + 1, end);
//    }
//
//    private static int pivotIndex(Integer[] array, int begin, int end) {
//        Integer pivotElement = array[begin];
//        end--;
//        while (begin < end) {
//            while (begin < end) {
//                if (array[end] > pivotElement) {
//                    end--;
//                } else {
//                    array[begin++] = array[end];
//                    break;
//                }
//            }
//            while (begin < end) {
//                if (array[begin] < pivotElement) {
//                    begin++;
//                } else {
//                    array[end--] = array[begin];
//                    break;
//                }
//            }
//        }
//        array[begin] = pivotElement;
//        return begin;
//    }
//
//    private static void mergeSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        Integer[] leftArray = new Integer[array.length >> 1];
//        mergeSort(leftArray, array, 0, array.length);
//        System.out.print("mergeSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void mergeSort(Integer[] leftArray, Integer[] array, int begin, int end) {
//        if (end - begin < 2) return;
//
//        int middle = (begin + end) >> 1;
//        mergeSort(leftArray, array, begin, middle);
//        mergeSort(leftArray, array, middle, end);
//        merge(leftArray, array, begin, middle, end);
//    }
//
//    private static void merge(Integer[] leftArray, Integer[] array, int begin, int middle, int end) {
//        int li = 0, le = middle - begin;
//        int ri = middle, re = end;
//        int ai = begin;
//        for (int i = 0; i < le; i++) {
//            leftArray[i] = array[begin + i];
//        }
//        while (li < le) {
//            if (ri < re && array[ri] < leftArray[li]) {
//                array[ai++] = array[ri++];
//            } else {
//                array[ai++] = leftArray[li++];
//            }
//        }
//    }
//
//    private static void insertionSort3(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int begin = 1; begin < array.length; begin++) {
//            int currentIndex = begin;
//            int currentElement = array[currentIndex];
//
//            int b = 0;
//            int e = begin;
//            while (b < e) {
//                int m = (b + e) >> 1;
//                if (currentElement < array[m]) {
//                    e = m;
//                } else {
//                    b = m + 1;
//                }
//            }
//
//            while (currentIndex > b) {
//                array[currentIndex] = array[currentIndex - 1];
//                currentIndex--;
//            }
//            array[b] = currentElement;
//        }
//        System.out.print("insertionSort3 : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void insertionSort2(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int begin = 1; begin < array.length; begin++) {
//            int currentIndex = begin;
//            int currentElement = array[currentIndex];
//            while (currentIndex > 0 && currentElement < array[currentIndex - 1]) {
//                array[currentIndex] = array[currentIndex - 1];
//                currentIndex--;
//            }
//            array[currentIndex] = currentElement;
//        }
//        System.out.print("insertionSort2 : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void insertionSort1(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int begin = 1; begin < array.length; begin++) {
//            int currentIndex = begin;
//            while (currentIndex > 0 && array[currentIndex] < array[currentIndex - 1]) {
//                swap(array, currentIndex, --currentIndex);
//            }
//        }
//        System.out.print("insertionSort1 : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void heapSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        int size = array.length;
//        for (int i = (array.length >> 1) - 1; i >= 0; i--) {
//            // 批量建堆：自下而上的下滤
//            siftDown(array, size, i);
//        }
//        while (size > 1) {
//            swap(array, 0, --size);
//            siftDown(array, size, 0);
//        }
//        System.out.print("heapSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void siftDown(Integer[] array, int size, int index) {
//        Integer currentElement = array[index];
//        int half = size >> 1;
//        while (index < half) {
//            int leftChildIndex = (index << 1) + 1;
//            Integer leftChildElement = array[leftChildIndex];
//            int rightChildIndex = leftChildIndex + 1;
//            if (rightChildIndex < size) {
//                Integer rightChildElement = array[rightChildIndex];
//                if (rightChildElement > leftChildElement) {
//                    leftChildElement = rightChildElement;
//                    leftChildIndex = rightChildIndex;
//                }
//            }
//            if (currentElement >= leftChildElement) break;
//            array[index] = leftChildElement;
//            index = leftChildIndex;
//        }
//        array[index] = currentElement;
//    }
//
//    private static void selectionSort(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int end = array.length - 1; end > 0; end--) {
//            int max = 0;
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin] > array[max]) {
//                    max = begin;
//                }
//            }
//            swap(array, max, end);
//        }
//        System.out.print("selectionSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void bubbleSort1(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int end = array.length - 1; end > 0; end--) {
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin - 1] > array[begin]) {
//                    swap(array, begin - 1, begin);
//                }
//            }
//        }
//        System.out.print("bubbleSort1 : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void bubbleSort2(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int end = array.length - 1; end > 0; end--) {
//            boolean sorted = true;
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin - 1] > array[begin]) {
//                    swap(array, begin - 1, begin);
//                    sorted = false;
//                }
//            }
//            if (sorted) break;
//        }
//        System.out.print("bubbleSort2 : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void bubbleSort3(Integer[] arr) {
//        Integer[] array = Arrays.copyOf(arr, arr.length);
//        for (int end = array.length - 1; end > 0; end--) {
//            int sorted = 1;
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin - 1] > array[begin]) {
//                    swap(array, begin - 1, begin);
//                    sorted = begin;
//                }
//            }
//            end = sorted;
//        }
//        System.out.print("bubbleSort3 : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void swap(Integer[] array, int index1, int index2) {
//        Integer temp = array[index1];
//        array[index1] = array[index2];
//        array[index2] = temp;
//    }

} */

    /*

    public class ReviewMain {

    public static void main(String[] args) {
//        Integer[] array = {45, 23, 78, 65, 11, 90, 39, 85, 56};
//        Integer[] array = Integers.random(100000, 1, 10000000);
//        Integer[] array = {-3, 8, 6, -7, 4, -5, 0};
//        Integer[] array = {3, 8, 6, 7, 4, 5};
        Integer[] array = {7, 3, 5, 8, 6, 7, 4, 5};
        System.out.print("original: ");
        Integers.println(array);
        bubbleSort(array);
        selectSort(array);
        insertSort(array);
        heapSort(array);
        mergeSort(array);
        quickSort(array);
        shellSort(array);
        countingSort(array);
    }

    private static void countingSort(Integer[] arr) {
        Integer[] array = Integers.copy(arr);
        System.out.print("countingSort: ");

        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        int[] tempArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            tempArray[--counts[array[i] - min]] = array[i];
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = tempArray[i];
        }

        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    private static void shellSort(Integer[] arr) {
        Integer[] array = Integers.copy(arr);
        System.out.print("shellSort: ");
        List<Integer> stepSequence = stepSequence(array);
        for (Integer step : stepSequence) {
            shellSort(array, step);
        }
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    private static void shellSort(Integer[] array, Integer step) {
        for (int col = 0; col < step; col++) {
            for (int begin = col + step; begin < array.length; begin += step) {
                int currentIndex = begin;
                Integer currentElement = array[currentIndex];
                while (currentIndex > col && currentElement < array[currentIndex - step]) {
                    array[currentIndex] = array[currentIndex - step];
                    currentIndex -= step;
                }
                array[currentIndex] = currentElement;
            }
        }
    }

    private static List<Integer> stepSequence(Integer[] array) {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    private static void bubbleSort(Integer[] arr) {
        System.out.print("bubbleSort: ");
        Integer[] array = Integers.copy(arr);
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
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }


    private static void selectSort(Integer[] arr) {
        System.out.print("selectSort: ");
        Integer[] array = Integers.copy(arr);
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] > array[maxIndex]) {
                    maxIndex = begin;
                }
            }
            swap(array, maxIndex, end);
        }
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    private static void insertSort(Integer[] arr) {
        System.out.print("insertSort: ");
        Integer[] array = Integers.copy(arr);
//        for (int begin = 1; begin < array.length; begin++) {
//            int current = begin;
//            while (current > 0 && array[current] < array[current - 1]) {
//                swap(array, current, current - 1);
//                current--;
//            }
//        }
        for (int begin = 1; begin < array.length; begin++) {
            int currentIndex = begin;
            Integer currentElement = array[currentIndex];
            int b = 0;
            int e = currentIndex;
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
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    private static void heapSort(Integer[] arr) {
        System.out.print("heapSort: ");
        Integer[] array = Integers.copy(arr);
        for (int i = (array.length >> 1) - 1; i >= 0; i--) {
            siftDown(array, array.length, i);
        }
        int size = array.length;
        while (size > 1) {
            swap(array, 0, size - 1);
            size--;
            siftDown(array, size, 0);
        }
        Integers.println(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    private static void siftDown(Integer[] array, int size, int index) {
        Integer currentElement = array[index];
        int half = size >> 1;
        while (index < half) {
            int leftChildIndex = (index << 1) + 1;
            Integer leftChildElement = array[leftChildIndex];
            int rightChildIndex = leftChildIndex + 1;
            if (rightChildIndex < size) {
                Integer rightChildElement = array[rightChildIndex];
                if (rightChildElement > leftChildElement) {
                    leftChildElement = rightChildElement;
                    leftChildIndex = rightChildIndex;
                }
            }
            if (currentElement >= leftChildElement) break;
            array[index] = leftChildElement;
            index = leftChildIndex;
        }
        array[index] = currentElement;
    }

    private static void mergeSort(Integer[] arr) {
        System.out.print("mergeSort: ");
        Integer[] array = Integers.copy(arr);
        Integer[] leftArray = new Integer[array.length >> 1];
        mergeSort(leftArray, array, 0, array.length);
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

    private static void quickSort(Integer[] arr) {
        System.out.print("quickSort: ");
        Integer[] array = Integers.copy(arr);
        quickSort(array, 0, array.length);
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
        Integer currentElement = array[begin];
        end--;
        while (begin < end) {
            while (begin < end) {
                if (array[end] > currentElement) {
                    end--;
                } else {
                    array[begin] = array[end];
                    begin++;
                    break;
                }
            }
            while (begin < end) {
                if (array[begin] < currentElement) {
                    begin++;
                } else {
                    array[end] = array[begin];
                    end--;
                    break;
                }
            }
        }
        array[begin] = currentElement;
        return begin;
    }

    public static final Integer[] ORIGINAL_ARRAY = Integers.random(100000, 1, 10000000);


    private static void swap(Integer[] array, int index1, int index2) {
        Integer temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //
//    private static void heapSort() {
//        Integer[] array = Arrays.copyOf(ORIGINAL_ARRAY, ORIGINAL_ARRAY.length);
//        for (int i = (array.length >> 1) - 1; i >= 0; i--) {
//            siftDown(array, array.length, i);
//        }
//        int size = array.length;
//        while (size > 1) {
//            int temp = array[--size];
//            array[size] = array[0];
//            array[0] = temp;
//            siftDown(array, size, 0);
//        }
//
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void siftDown(Integer[] array, int size, int index) {
//        Integer current = array[index];
//        int half = size >> 1;
//        while (index < half) {
//            int leftChildIndex = (index << 1) + 1;
//            Integer leftChild = array[leftChildIndex];
//            int rightChildIndex = leftChildIndex + 1;
//            if (rightChildIndex < size) {
//                Integer rightChild = array[rightChildIndex];
//                if (rightChild > leftChild) {
//                    leftChildIndex = rightChildIndex;
//                    leftChild = rightChild;
//                }
//            }
//            if (current >= leftChild) break;
//            array[index] = leftChild;
//            index = leftChildIndex;
//        }
//        array[index] = current;
//    }
//
//    private static void mergeSort() {
//        Integer[] array = Arrays.copyOf(ORIGINAL_ARRAY, ORIGINAL_ARRAY.length);
//        Integer[] leftArray = new Integer[array.length >> 1];
//        mergeSort(array, leftArray, 0, array.length);
//
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void mergeSort(Integer[] array, Integer[] leftArray, int begin, int end) {
//        if (end - begin < 2) return;
//        int middle = (begin + end) >> 1;
//        mergeSort(array, leftArray, begin, middle);
//        mergeSort(array, leftArray, middle, end);
//        merge(array, leftArray, begin, middle, end);
//    }
//
//    private static void merge(Integer[] array, Integer[] leftArray, int begin, int middle, int end) {
//        int li = 0, le = middle - begin;
//        int ri = middle, re = end;
//        int ai = begin;
//        for (int i = 0; i < le; i++) {
//            leftArray[i] = array[begin + i];
//        }
//        while (li < le) {
//            if (ri < re && array[ri] < leftArray[li]) {
//                array[ai++] = array[ri++];
//            } else {
//                array[ai++] = leftArray[li++];
//            }
//        }
//    }
//
//    private static void quickSort() {
//        Integer[] array = Arrays.copyOf(ORIGINAL_ARRAY, ORIGINAL_ARRAY.length);
//        quickSort(array, 0, array.length);
//
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void quickSort(Integer[] array, int begin, int end) {
//        if (end - begin < 2) return;
//        int pivotIndex = pivotIndex(array, begin, end);
//        quickSort(array, begin, pivotIndex);
//        quickSort(array, pivotIndex + 1, end);
//    }
//
//    private static int pivotIndex(Integer[] array, int begin, int end) {
//        final int swapIndex = (int) (Math.random() * (end - begin)) + begin;
//        Integer temp = array[swapIndex];
//        array[swapIndex] = array[begin];
//        array[begin] = temp;
//
//        Integer pivotElement = array[begin];
//        end--;
//
//        while (begin < end) {
//            // 从右往左扫描
//            while (begin < end) {
//                if (pivotElement < array[end]) {
//                    end--;
//                } else {
//                    array[begin] = array[end];
//                    begin++;
//                    break;
//                }
//            }
//            // 从左往右扫描
//            while (begin < end) {
//                if (array[begin] < pivotElement) {
//                    begin++;
//                } else {
//                    array[end] = array[begin];
//                    end--;
//                    break;
//                }
//            }
//        }
//        array[begin] = pivotElement;
//        return begin;
//    }

//
//    private static Integer[] leftArray;
//
//    private static void mergeSort() {
//        Integer[] array = Integers.copy(ORIGINAL_ARRAY);
//        leftArray = new Integer[array.length >> 1];
//        mergeSort(array, 0, array.length);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void mergeSort(Integer[] array, int begin, int end) {
//        if (end - begin < 2) return;
//        int middle = (begin + end) >> 1;
//        mergeSort(array, begin, middle);
//        mergeSort(array, middle, end);
//        merge(array, begin, middle, end);
//    }
//
//    private static void merge(Integer[] array, int begin, int middle, int end) {
//        int li = 0, le = middle - begin;
//        int ri = middle, re = end;
//        int ai = begin;
//        for (int i = 0; i < le; i++) {
//            leftArray[i] = array[begin + i];
//        }
//        while (li < le) {
//            if (ri < re && array[ri] < leftArray[li]) {
//                array[ai++] = array[ri++];
//            } else {
//                array[ai++] = leftArray[li++];
//            }
//        }
//    }
//
//
//    private static void insertionSort() {
//        Integer[] array = Integers.copy(ORIGINAL_ARRAY);
//        for (int begin = 1, len = array.length - 1; begin <= len; begin++) {
//            int current = begin;
//            Integer currentElement = array[current];
//            int b = 0;
//            int e = begin;
//            while (b < e) {
//                int m = (b + e) >> 1;
//                if (array[current] < array[m]) {
//                    e = m;
//                } else {
//                    b = m + 1;
//                }
//            }
//            while (current > b) {
//                array[current] = array[current - 1];
//                current--;
//            }
//            array[b] = currentElement;
//        }
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void bubbleSort() {
//        Integer[] array = Integers.copy(ORIGINAL_ARRAY);
//        for (int end = array.length - 1; end > 0; end--) {
//            int sortedIndex = 1;
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin - 1] > array[begin]) {
//                    swap(array, begin - 1, begin);
//                    sortedIndex = begin;
//                }
//            }
//            end = sortedIndex;
//        }
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void selectionSort() {
//        Integer[] array = Integers.copy(ORIGINAL_ARRAY);
//        for (int end = array.length - 1; end > 0; end--) {
//            int maxIndex = 0;
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin] > array[maxIndex]) {
//                    maxIndex = begin;
//                }
//            }
//            swap(array, maxIndex, end);
//        }
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void swap(Integer[] array, int index1, int index2) {
//        Integer temp = array[index1];
//        array[index1] = array[index2];
//        array[index2] = temp;
//    }

//
//    private static int[] leftArray;
//    private static int[] array = {23, 56, 34, 68, 12, 47};
//
//    private static void mergeSort() {
//        leftArray = new int[array.length >> 1];
//        mergeSort(0, array.length);
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//    }
//
//    private static void mergeSort(int begin, int end) {
//        if (end - begin < 2) return;
//        int middle = (begin + end) >> 1;
//        mergeSort(begin, middle);
//        mergeSort(middle, end);
//        mergeSortMerge(begin, middle, end);
//    }
//
//    private static void mergeSortMerge(int begin, int middle, int end) {
//        int li = 0, le = middle - begin;
//        int ri = middle, re = end;
//        int ai = begin;
//        for (int i = 0; i < le; i++) {
//            leftArray[i] = array[begin + i];
//        }
//        while (li < le) {
//            if (ri < re && array[ri] < leftArray[li]) {
//                array[ai++] = array[ri++];
//            } else {
//                array[ai++] = leftArray[li++];
//            }
//        }
//    }
//
//    private static final int ELEMENT_NOT_FOUND = -1;
//
//    public static int indexOf(Integer[] array, Integer value) {
//        if (array == null || array.length == 0) return ELEMENT_NOT_FOUND;
//        int begin = 0;
//        int end = array.length;
//        while (begin < end) {
//            int middle = (begin + end) >> 1;
//            if (value < array[middle]) {
//                end = middle;
//            } else if (value > array[middle]) {
//                begin = middle + 1;
//            } else {
//                return middle;
//            }
//        }
//        return ELEMENT_NOT_FOUND;
//    }
//
//    private static int searchInsertIndex(Integer[] array, Integer value) {
//        if (array == null || array.length == 0) return ELEMENT_NOT_FOUND;
//        int begin = 0;
//        int end = array.length;
//        while (begin < end) {
//            int middle = (begin + end) >> 1;
//            if (value < array[middle]) {
//                end = middle;
//            } else {
//                begin = middle + 1;
//            }
//        }
//        return begin;
//    }

    //    public static void main1(String[] args) {
//        mergeSort();
//        Integer[] array = new Integer[]{2, 4, 8, 12, 14};
//        Asserts.test(indexOf(array, 4) == 1);
//        Asserts.test(indexOf(array, 2) == 0);
//        Asserts.test(indexOf(array, 12) == 3);
//        Asserts.test(indexOf(array, 14) == 4);
//        array = new Integer[]{2, 4, 8, 8, 8, 12, 14};
//        Asserts.test(searchInsertIndex(array, 5) == 2);
//        Asserts.test(searchInsertIndex(array, 1) == 0);
//        Asserts.test(searchInsertIndex(array, 15) == 7);
//        Asserts.test(searchInsertIndex(array, 8) == 5);
//    }


    * */

//    ------------------------
//    public class SortReview {
//        public static void main(String[] args) {
//            final Integer[] random = Integers.random(10, 1, 21000000);
//            Integers.println(random);
//            mergeSort(random);
//        }
//
//        private static void mergeSort(Integer[] arr) {
//            final Integer[] array = Integers.copy(arr);
//            mergeSort(array, 0, array.length);
//            Integers.println(array);
//            Asserts.test(Integers.isAscOrder(array));
//        }
//
//        private static void mergeSort(Integer[] array, int begin, int end) {
//            if (end - begin < 2) return;
//            int middle = (begin + end) >> 1;
//            mergeSort(array, begin, middle);
//            mergeSort(array, middle, end);
//            merge(array, begin, middle, end);
//        }
//
//        private static void merge(Integer[] array, int begin, int middle, int end) {
//            Integer[] leftArray = new Integer[middle - begin];
//            for (int i = 0; i < leftArray.length; i++) {
//                leftArray[i] = array[begin + i];
//            }
//            int li = 0, le = leftArray.length;
//            int ri = middle, re = end;
//            int ai = begin;
//            while (li < le) {
//                if (ri < re && array[ri] < leftArray[li]) {
//                    array[ai++] = array[ri++];
//                } else {
//                    array[ai++] = leftArray[li++];
//                }
//            }
//        }
//    }

// ------------------------------------
//
////    private static final Integer[] ARRAYS = {44, 42, 19, 16, 40, 45, 11, 3, 38, 11};
//    private static final Integer[] ARRAYS = Integers.random(1000, 1, 150000);
//
//    public static void main(String[] args) {
//        System.out.print("original : ");
//        Integers.println(ARRAYS);
//        bubbleSort();
//        selectionSort();
//        heapSort();
//        insertionSort();
//        mergeSort();
//        quickSort();
//        shellSort();
//        countingSort();
//    }
//
//    private static void countingSort() {
//        Integer[] array = Integers.copy(ARRAYS);
//        Integer max = array[0];
//        Integer min = array[0];
//        for (int begin = 1; begin < array.length; begin++) {
//            if (array[begin] > max) {
//                max = array[begin];
//            }
//            if (array[begin] < min) {
//                min = array[begin];
//            }
//        }
//        int[] counts = new int[max - min + 1];
//        for (int i = 0; i < array.length; i++) {
//            counts[array[i] - min]++;
//        }
//        for (int i = 1; i < counts.length; i++) {
//            counts[i] += counts[i - 1];
//        }
//        Integer[] tempArray = new Integer[array.length];
//        for (int i = array.length - 1; i >= 0; i--) {
//            tempArray[--counts[array[i] - min]] = array[i];
//        }
//        for (int i = 0; i < array.length; i++) {
//            array[i] = tempArray[i];
//        }
//        System.out.print("countingSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void shellSort() {
//        Integer[] array = Integers.copy(ARRAYS);
//        List<Integer> stepSequence = stepSequence(array.length);
//        for (Integer step : stepSequence) {
//            for (int col = 0; col < step; col++) {
//                for (int begin = col + step; begin < array.length; begin += step) {
//                    int currentIndex = begin;
//                    int currentElement = array[currentIndex];
//                    while (currentIndex > col && currentElement < array[currentIndex - step]) {
//                        array[currentIndex] = array[currentIndex - step];
//                        currentIndex -= step;
//                    }
//                    array[currentIndex] = currentElement;
//                }
//            }
//        }
//        System.out.print("shellSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static List<Integer> stepSequence(int step) {
//        List<Integer> stepSequence = new ArrayList<>();
//        while ((step >>= 1) > 0) {
//            stepSequence.add(step);
//        }
//        return stepSequence;
//    }
//
//    private static void quickSort() {
//        Integer[] array = Integers.copy(ARRAYS);
//        quickSort(array, 0, array.length);
//        System.out.print("quickSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void quickSort(Integer[] array, int begin, int end) {
//        if (end - begin < 2) return;
//        int pivotIndex = pivotIndex(array, begin, end);
//        quickSort(array, begin, pivotIndex);
//        quickSort(array, pivotIndex + 1, end);
//    }
//
//    private static int pivotIndex(Integer[] array, int begin, int end) {
//        Integer pivot = array[begin];
//        end--;
//        while (begin < end) {
//            while (begin < end) {
//                if (array[end] > pivot) {
//                    end--;
//                } else {
//                    array[begin++] = array[end];
//                    break;
//                }
//            }
//            while (begin < end) {
//                if (array[begin] < pivot) {
//                    begin++;
//                } else {
//                    array[end--] = array[begin];
//                    break;
//                }
//            }
//        }
//        array[begin] = pivot;
//        return begin;
//    }
//
//    private static void mergeSort() {
//        Integer[] array = Integers.copy(ARRAYS);
//        mergeSort(array, 0, array.length);
//        System.out.print("mergeSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void mergeSort(Integer[] array, int begin, int end) {
//        if (end - begin < 2) return;
//        int middle = (begin + end) >> 1;
//        mergeSort(array, begin, middle);
//        mergeSort(array, middle, end);
//        merge(array, begin, middle, end);
//    }
//
//    private static void merge(Integer[] array, int begin, int middle, int end) {
//        Integer[] leftArray = new Integer[middle - begin];
//        int li = 0, le = middle - begin;
//        int ri = middle, re = end;
//        int ai = begin;
//        for (int i = 0; i < le; i++) {
//            leftArray[i] = array[begin + i];
//        }
//        while (li < le) {
//            if (ri < re && array[ri] < leftArray[li]) {
//                array[ai++] = array[ri++];
//            } else {
//                array[ai++] = leftArray[li++];
//            }
//        }
//    }
//
//    private static void insertionSort() {
//        Integer[] array = Integers.copy(ARRAYS);
//        for (int begin = 1; begin < array.length; begin++) {
//            int currentIndex = begin;
//            Integer currentElement = array[currentIndex];
//            while (currentIndex > 0 && currentElement < array[currentIndex - 1]) {
//                array[currentIndex] = array[currentIndex - 1];
//                currentIndex--;
//            }
//            array[currentIndex] = currentElement;
//        }
//        System.out.print("insertionSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void heapSort() {
//        Integer[] array = Integers.copy(ARRAYS);
//        int heapSize = array.length;
//        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
//            siftDown(array, heapSize, i);
//        }
//        while (heapSize > 1) {
//            swap(array, 0, --heapSize);
//            siftDown(array, heapSize, 0);
//        }
//        System.out.print("heapSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void siftDown(Integer[] array, int heapSize, int index) {
//        Integer currentElement = array[index];
//        int half = heapSize >> 1;
//        while (index < half) {
//            int leftChildIndex = (index << 1) + 1;
//            int rightChildIndex = leftChildIndex + 1;
//            Integer leftChildElement = array[leftChildIndex];
//            if (rightChildIndex < heapSize) {
//                Integer rightChildElement = array[rightChildIndex];
//                if (rightChildElement > leftChildElement) {
//                    leftChildIndex = rightChildIndex;
//                    leftChildElement = rightChildElement;
//                }
//            }
//            if (currentElement >= leftChildElement) break;
//            array[index] = leftChildElement;
//            index = leftChildIndex;
//        }
//        array[index] = currentElement;
//    }
//
//    private static void selectionSort() {
//        Integer[] array = Integers.copy(ARRAYS);
//        for (int end = array.length - 1; end > 0; end--) {
//            int maxIndex = 0;
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin] > array[maxIndex]) {
//                    maxIndex = begin;
//                }
//            }
//            swap(array, maxIndex, end);
//        }
//        System.out.print("selectionSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void bubbleSort() {
//        Integer[] array = Integers.copy(ARRAYS);
//        for (int end = array.length - 1; end > 0; end--) {
//            int sortedIndex = 1;
//            for (int begin = 1; begin <= end; begin++) {
//                if (array[begin - 1] > array[begin]) {
//                    swap(array, begin - 1, begin);
//                    sortedIndex = begin;
//                }
//            }
//            end = sortedIndex;
//        }
//        System.out.print("bubbleSort : ");
//        Integers.println(array);
//        Asserts.test(Integers.isAscOrder(array));
//    }
//
//    private static void swap(Integer[] array, int i1, int i2) {
//        Integer temp = array[i1];
//        array[i1] = array[i2];
//        array[i2] = temp;
//    }

}
