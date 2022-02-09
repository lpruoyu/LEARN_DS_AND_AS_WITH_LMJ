package taught_by_mjlmj.steptwo.sort.other;

import taught_by_mjlmj.steptwo.sort.Sort;

public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int[] counts = new int[10];
        int[] tempArray = new int[array.length];
        for (int divider = 1; divider <= max; divider *= 10) {
            countingSort(divider, counts, tempArray);
        }
    }

    private void countingSort(int divider, int[] counts, int[] tempArray) {
        for (int i = 1; i < counts.length; i++) {
            counts[i] = 0;
        }
        for (int i = 0; i < array.length; i++) {
            counts[array[i] / divider % 10]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            tempArray[--counts[array[i] / divider % 10]] = array[i];
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = tempArray[i];
        }
    }
}