package taught_by_mjlmj.steptwo.sort.cmp;

import taught_by_mjlmj.steptwo.sort.Sort;

/* 每一轮循环找出一个最大元素的索引，然后和数组末尾的元素交换 */
public class SelectionSort<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(array[begin], array[maxIndex]) >= 0) { // 稳定
//                if (cmp(array[begin], array[maxIndex]) > 0) { // 不稳定
                    maxIndex = begin;
                }
            }
            swap(end, maxIndex);
        }
    }

    public static void sort1(Integer[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] > arr[maxIndex]) {
                    maxIndex = begin;
                }
            }
            int temp = arr[end];
            arr[end] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
    }

}