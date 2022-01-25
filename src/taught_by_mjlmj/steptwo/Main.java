package taught_by_mjlmj.steptwo;

import taught_by_mjlmj.steptwo.help.BinarySearch;
import taught_by_mjlmj.steptwo.help.NBThreadSort;
import taught_by_mjlmj.steptwo.sort.CountingSort;
import taught_by_mjlmj.steptwo.sort.RadixSort;
import taught_by_mjlmj.steptwo.sort.Sort;
import taught_by_mjlmj.tools.Asserts;
import taught_by_mjlmj.tools.Integers;

import java.util.Arrays;

/* 默认实现升序排序 */
/* 升序排序和降序排序不是一样的吗？不是吗？ */

public class Main {

    public static final Integer[] array = {
            13, 91, 52, 66, 20, 45, 85, 74, 37
    };

    public static final Integer[] ORIGINAL_ARRAY = Integers.random(1000, 1, 10000);

    public static void main(String[] args) {
        radixSortTest();
//        countingSortTest();
//        sortThreadTest();
//        searchInsertTest();
//        binarySearchTest();
//        sortTest(new BubbleSort<>());
//        sortTest(new SelectionSort<>());
//        sortTest(new HeapSort<>());
//        sortTest(new InsertionSort<>());
        testSortWithThread(
//                new BubbleSort<>(),
//                new SelectionSort<>(),
//                new InsertionSort<>(),
//                new HeapSort<>(),
//                new MergeSort<>(),
//                new QuickSort<>(),
//                new ShellSort<>(),
//                new CountingSort(),
//                new RadixSort()
        );
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static void radixSortTest() {
//        Integer[] array = {7, 3, 5, 8, 6, 7, 4, 5};
//        Integer[] array = {3, 228, 6, 27, 4, 5};
        Integer[] array = Integers.copy(ORIGINAL_ARRAY);
        RadixSort sort = new RadixSort();
        sort.sort(array);
        Asserts.test(Integers.isAscOrder(array));
        Integers.println(array);
        System.out.println(sort);
    }

    private static void countingSortTest() {
//        Integer[] array = {7, 3, 5, 8, 6, 7, 4, 5};
//        Integer[] array = {3, 8, 6, 7, 4, 5};
        Integer[] array = {-3, 8, 6, -7, 4, -5, 0};
//        Integer[] array = Integers.copy(ORIGINAL_ARRAY);
        CountingSort sort = new CountingSort();
        sort.sort(array);
        Asserts.test(Integers.isAscOrder(array));
        Integers.println(array);
        System.out.println(sort);
    }

    private static void sortThreadTest() {
        Arrays.sort(array);
        int[] array = {550, 10, 50, 100, 180};
        for (int i = 0; i < array.length; i++) {
            new NBThreadSort(array[i]).start();
        }
    }

    private static void searchInsertTest() {
        Integer[] array = {2, 4, 8, 8, 8, 12, 14};
        Asserts.test(BinarySearch.searchInsertIndex(array, 5) == 2);
        Asserts.test(BinarySearch.searchInsertIndex(array, 1) == 0);
        Asserts.test(BinarySearch.searchInsertIndex(array, 15) == 7);
        Asserts.test(BinarySearch.searchInsertIndex(array, 8) == 5);
    }

    private static void binarySearchTest() {
        Integer[] array = {2, 4, 6, 8, 10};
        Asserts.test(BinarySearch.indexOf(array, 1) == BinarySearch.ELEMENT_NOT_FOUND);
        Asserts.test(BinarySearch.indexOf(array, 8) == 3);
    }

    private static void testSortWithThread(Sort<Integer>... sorts) {
        for (Sort<Integer> sort : sorts) {
            new Thread() {
                @Override
                public void run() {
                    Integer[] newArray = Integers.copy(ORIGINAL_ARRAY);
                    sort.sort(newArray);
                    Asserts.test(Integers.isAscOrder(newArray));
                    System.out.println(sort);
                }
            }.start();
        }
    }

    private static void testSort(Sort<Integer>... sorts) {
        for (Sort<Integer> sort : sorts) {
            Integer[] newArray = Integers.copy(ORIGINAL_ARRAY);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);
        for (Sort<Integer> sort : sorts) {
            System.out.println(sort);
        }
    }

    private static void sortTest(Sort<Integer> sort) {
        Integer[] array = Integers.copy(ORIGINAL_ARRAY);
//        Integers.println(array);
        sort.sort(array);
//        Integers.println(array);
        System.out.println(sort);
        Asserts.test(Integers.isAscOrder(array));
    }

}