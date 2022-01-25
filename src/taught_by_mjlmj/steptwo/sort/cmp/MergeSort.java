package taught_by_mjlmj.steptwo.sort.cmp;

import taught_by_mjlmj.steptwo.sort.Sort;

// 牢记左闭右开
public class MergeSort<E extends Comparable<E>> extends Sort<E> {

    private E[] leftArray;

    @Override
    protected void sort() {
        leftArray = (E[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) return; // 如果只有一个元素就不用排序了
        int middle = (begin + end) >> 1;
        sort(begin, middle);
        sort(middle, end);
        merge(begin, middle, end);
    }

    private void merge(int begin, int middle, int end) {
        int li = 0, le = middle - begin;
        int ri = middle, re = end;
        int ai = begin;
//        for (int i = 0; i < le; i++) {
//            leftArray[i] = array[begin + i];
//        }
        System.arraycopy(array, begin, leftArray, 0, le);
        while (li < le) { // 左边结束后，就不用做其他操作了
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }

}
