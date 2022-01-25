package taught_by_mjlmj.steptwo.sort.cmp;

import taught_by_mjlmj.steptwo.sort.Sort;

public class QuickSort<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
        quickSort(0, array.length);
    }

    /**
     * 对[begin, end)范围内的元素进行快速排序
     *
     * @param begin
     * @param end
     */
    private void quickSort(int begin, int end) {
        if (end - begin < 2) return;
        int pivotIndex = pivotIndex(begin, end);// 构造轴点并返回轴点位置
        quickSort(begin, pivotIndex); // 对左子序列进行快排
        quickSort(pivotIndex + 1, end); // 对右子序列进行快排
    }

    // 构造轴点并返回轴点位置
    private int pivotIndex(int begin, int end) {
        // Math.random() -> [0, 1)
        // Math.random() * (end - begin) -> [0, end - begin)
        // begin + Math.random() * (end - begin) -> begin + [0, end - begin) -> [begin, end)
        // 随机选取轴点元素
        swap(begin, begin + (int) (Math.random() * (end - begin)));

        E pivot = array[begin]; // 备份begin位置的元素
        end--; // 由于首先是从右往左扫描，所以要将  end--  一次
        while (begin < end) {
            while (begin < end) {
                // 从右往左扫描（注意观察小于号的箭头方向）
                if (cmp(pivot, array[end]) < 0) { // 如果end位置的元素大于pivot元素
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin < end) {
                // 从左往右扫描（注意观察大于号的箭头方向）
                if (cmp(pivot, array[begin]) > 0) { // 如果begin位置的元素小于pivot元素
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        array[begin] = pivot; // 将备份的元素放到pivot轴点处
        return begin; // 返回begin或end都可以，此时begin == end
        // return end;
    }

}