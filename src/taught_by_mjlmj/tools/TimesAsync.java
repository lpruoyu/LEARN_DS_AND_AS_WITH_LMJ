package taught_by_mjlmj.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimesAsync {

    private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");

    public interface Task {
        void execute();
    }

    public static void test(String title, Task task) {
        if (task == null) return;
        new Thread(() -> {
            System.out.println((title == null) ? "" : ("【" + title + "】"));
            System.out.println("开始：" + fmt.format(new Date()));
            final long begin = System.currentTimeMillis();
            task.execute();
            final long end = System.currentTimeMillis();
            System.out.println("结束：" + fmt.format(new Date()));
            final double delta = (end - begin) / 1000.0;
            System.out.println("耗时：" + delta + "秒");
            System.out.println("-------------------------------------");
        }).start();
    }

}
