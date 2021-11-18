package taught_by_mjlmj.stepone.other;

public class Fibonacci {

    /*
        多种方法实现斐波那契数列：0 1 1 2 3 5 8 13 21 ...
     */

    // 递归实现
    public static int fib1(int n) {
        if (n <= 1) return n;
        return fib1(n - 2) + fib1(n - 1);
    }

    // 非递归实现
    public static long fib2(int n) {
        if (n <= 1) return n;
        long first = 0;
        long second = 1;
        long sum;
        for (int i = 0; i < n - 1; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    /*
        数组缓存实现
        斐波那契数列：0 1 1 2 3 5 8 13 21...
     */
    public static int fib3(int n) {
//        if (n == 0) return 0;
//        else if (n == 1 || n == 2) return 1;
//        else {
//            final int[] buff = new int[n + 1];
//            buff[0] = 0;
//            buff[1] = 1;
//            buff[2] = 1;
//            return fib3(buff, n);
//        }

        final int[] buff = new int[n + 3];
        buff[0] = 0;
        buff[1] = 1;
        buff[2] = 1;

        if (n <= 2) {
            if (n == 0) return 0;
            else if (n == 1 || n == 2) return 1;
            else return -1;
        }

        return fib3(buff, n);
    }

    private static int fib3(int[] buff, int n) {
        if (buff[n] == 0) {
            buff[n] = fib3(buff, n - 1) + fib3(buff, n - 2);
        }
        return buff[n];
    }

    /*
        fib3数组缓存是从n开始，然后到n-1，n-2，n-3......，显然会有重复调用
        可以考虑从0，1，2，3，4...... n实现
    */
    private static int fib4(int n) {
//        if (n == 0) return 0;
//        else if (n == 1 || n == 2) return 1;
//        else {
//            final int[] buff = new int[n + 1];
//            buff[0] = 0;
//            buff[1] = 1;
//            buff[2] = 1;
//
//            int current = 3;
//            while (current <= n) {
//                buff[current] = buff[current - 1] + buff[current - 2];
//                current++;
//            }
//            return buff[n];
//        }

        final int[] buff = new int[n + 3]; // 确保至少有3个容量大小
        buff[0] = 0;
        buff[1] = 1;
        buff[2] = 1;

        if (n >= 3) {
            int current = 3;
            while (current <= n) {
                buff[current] = buff[current - 1] + buff[current - 2];
                current++;
            }
        }

        return buff[n];

    }


    /*
        滚动数组来实现
    */
    private static long fib5(int n) {
        if (n <= 1) return n;
        final long[] buff = new long[2];
        buff[0] = 0;
        buff[1] = 1;
        for (int i = 1; i < n; i++) {
            final long temp = buff[0] + buff[1];
            if (buff[0] < buff[1]) {
                buff[0] = temp;
            } else {
                buff[1] = temp;
            }
        }
        return Math.max(buff[0], buff[1]);
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 50; i++) {   // 0 1 1 2 3 5 8 13 21
//            System.out.print(fib5(i) + " ");
//        }
//        final int n = 70;
//        System.out.println(fib2(n));
//        System.out.println(fib5(n));

        final int a = 1; // 0b0001 & 0b0001  =  0b0001  =  1
        final int b = 2; // 0b0010 & 0b0001  =  0b0000  =  0
        final int c = 3; // 0b0011 & 0b0001  =  0b0001  =  1
        final int d = 4; // 0b0100 & 0b0001  =  0b0000  =  0

    }

}
