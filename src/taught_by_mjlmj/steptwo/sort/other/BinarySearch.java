package taught_by_mjlmj.steptwo.sort.other;

public class BinarySearch {

    public static final int ELEMENT_NOT_FOUND = -1;

    /* 左闭右开 */

    public static int indexOf(Integer[] array, int value) {
        if (null == array || array.length == 0) return ELEMENT_NOT_FOUND;
        int begin = 0;
        int end = array.length;
        while (begin < end) { // end - begin > 0 => 该区间范围内的元素个数>0
            int mid = (begin + end) >> 1;
            Integer midElement = array[mid];
            if (value < midElement) {
                end = mid;
            } else if (value > midElement) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    // 辅助插入排序
    // 要求获取第一个大于element的位置
    public static int searchInsertIndex(Integer[] array, int value) {
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            Integer midElement = array[mid];
            if (value < midElement) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }

}
