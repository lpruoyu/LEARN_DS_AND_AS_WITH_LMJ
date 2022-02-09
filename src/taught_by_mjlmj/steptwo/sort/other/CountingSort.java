package taught_by_mjlmj.steptwo.sort.other;

import taught_by_mjlmj.steptwo.sort.Sort;

public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        // 找出最大最小值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 开辟新的counts计数数组
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        // 创建一个临时数组，用于存放排序元素
        int[] tempArray = new int[array.length];
        // 排序 从右往左可以保证稳定性
        for (int i = array.length - 1; i >= 0; i--) {
//            counts[array[i] - min]--;
//            tempArray[counts[array[i] - min]] = array[i];
            tempArray[--counts[array[i] - min]] = array[i];
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = tempArray[i];
        }
    }

    protected void sortSimple() {
        // 找出最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 构建一个计数数组
        int[] counts = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }
        // 排序
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
                array[index++] = i;
            }
        }
    }
}