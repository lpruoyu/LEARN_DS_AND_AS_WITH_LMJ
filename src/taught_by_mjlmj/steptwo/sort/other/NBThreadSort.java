package taught_by_mjlmj.steptwo.sort.other;

// 休眠排序
public class NBThreadSort extends Thread {

    private final int value;

    public NBThreadSort(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(value);
            System.out.print(value + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
