package taught_by_mjlmj.steptwo.sort;

import java.util.Comparator;

public abstract class SortByMe<E extends Comparable<E>> {

    private Comparator<E> comparator;
    protected E[] array;
    protected int cmpCount; // 比较次数
    protected int swapCount; // 交换次数

    public SortByMe() {
        this(null);
    }

    public SortByMe(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void sort(E[] array) {
        if (null == array || array.length < 2) {
            return;
        }
        this.array = array;
        sort();
        System.out.println(this.getClass().getSimpleName() + " 比较次数：" + cmpCount + " 交换次数：" + swapCount);
    }

    protected int compare(E e1, E e2) {
        cmpCount++;
        return comparator == null ? e1.compareTo(e2) : comparator.compare(e1, e2);
    }

    protected void swap(int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;

        swapCount++;
    }

    protected abstract void sort();

}