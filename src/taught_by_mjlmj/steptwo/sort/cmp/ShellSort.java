package taught_by_mjlmj.steptwo.sort.cmp;

import taught_by_mjlmj.steptwo.sort.Sort;

import java.util.ArrayList;
import java.util.List;

public class ShellSort<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
//        希尔排序可自定义步长序列
//        List<Integer> stepSequence = stepSequence();
        List<Integer> stepSequence = sedgewickStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    // 分为 step 列进行排序
    private void sort(Integer step) {
        for (int col = 0; col < step; col++) {
            // col + row * step;
            // col, col + step, col + 2 * step,    col + 3 * step...
            // col, col + step, col + step + step, col + step + step + step...
//            for (int begin = col + step; begin < array.length; begin += step) {
//                int current = begin;
//                while (current > col && cmp(current, current - step) < 0) {
//                    swap(current, current - step);
//                    current -= step;
//                }
//            }
            for (int begin = col + step; begin < array.length; begin += step) {
                int current = begin;
                E element = array[current];
                while (current > col && cmp(element, array[current - step]) < 0) {
                    array[current] = array[current - step];
                    current -= step;
                }
                array[current] = element;
            }
        }
    }

    private List<Integer> stepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    private List<Integer> sedgewickStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int k = 0, step;
        while (true) {
            if ((k & 1) == 0) {
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            } else {
                int pow1 = (int) Math.pow(2, (k - 1) >> 1);
                int pow2 = (int) Math.pow(2, (k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if (step >= array.length) break;
            stepSequence.add(0, step);
            k++;
        }
        return stepSequence;
    }

}