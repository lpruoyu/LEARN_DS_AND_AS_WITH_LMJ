package taught_by_mjlmj.steptwo.sort.cmp;

import taught_by_mjlmj.steptwo.sort.Sort;

/* 从1开始扫描每一个元素，将这个元素和前面已经排好序的元素进行比较，并插入到合适的位置 */
public class InsertionSort<E extends Comparable<E>> extends Sort<E> {

    // 抽取代码
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            insert(begin);
        }
    }

    private void insert(int current) {
        E element = array[current];
        int insertIndex = searchInsertIndex(current);
        while (current > insertIndex) {
            array[current] = array[current - 1];
            current--;
        }
        array[insertIndex] = element;
    }

    private int searchInsertIndex(int current) {
        // 要求获取第一个大于element的位置
        int begin = 0;
        int end = current;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(current, mid) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
//        return end; // 返回begin或者end都行
    }

    // 优化：使用二分搜索确定插入的位置，减少比较次数
    protected void sort3() {
        for (int begin = 1; begin < array.length; begin++) {
            int current = begin;
            E element = array[current];

            // 要求获取第一个大于element的位置
            int b = 0;
            int e = current;
            while (b < e) {
                int m = (b + e) >> 1;
                E midElement = array[m];
                if (cmp(element, midElement) < 0) {
                    e = m;
                } else {
                    b = m + 1;
                }
            }

            while (current > b) {
                array[current] = array[current - 1];
                current--;
            }
            array[b] = element;
        }
    }

    // 优化：将“交换”更改为“挪动”
    protected void sort2() {
        for (int begin = 1; begin < array.length; begin++) {
            int current = begin;
            E element = array[current];
            while (current > 0 && cmp(element, array[current - 1]) < 0) {
                array[current] = array[current - 1];
                current--;
            }
            array[current] = element;
        }
    }

    // 交换
    protected void sort1() {
        for (int begin = 1; begin < array.length; begin++) {
            int current = begin;
            while (current > 0 && cmp(current, current - 1) < 0) {
                swap(current, current - 1);
                current--;
            }
        }
    }

}