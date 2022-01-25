package taught_by_mjlmj.steptwo.sort.cmp;

import taught_by_mjlmj.steptwo.sort.Sort;

/* 两两比较，进行交换，每一轮循环会确定一个最大的元素到数组末尾 */
public class BubbleSort<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(array[begin - 1], array[begin]) > 0) {
                    swap(begin - 1, begin);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }

    public static void sort3(Integer[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            // sortedIndex在元素已经排好序时有用
            // 如果有序的话，初始值赋为1，1经过内层循环后不会改变，1-0 <= 0，就可以让循环结束
            // 如果不有序的话，sortedIndex会记录最后一次交换的位置
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin - 1] > arr[begin]) {
                    int temp = arr[begin - 1];
                    arr[begin - 1] = arr[begin];
                    arr[begin] = temp;
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }

    public static void sort2(Integer[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin - 1] > arr[begin]) {
                    int temp = arr[begin - 1];
                    arr[begin - 1] = arr[begin];
                    arr[begin] = temp;
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

    public static void sort1(Integer[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin - 1] > arr[begin]) {
                    int temp = arr[begin - 1];
                    arr[begin - 1] = arr[begin];
                    arr[begin] = temp;
                }
            }
        }
    }

}