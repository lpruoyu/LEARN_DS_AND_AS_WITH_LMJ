package taught_by_mjlmj.steptwo.unionfind;

import taught_by_mjlmj.steptwo.unionfind.bean.Student;
import taught_by_mjlmj.tools.Asserts;
import taught_by_mjlmj.tools.Times;

public class Main {
    static final int count = 1000000;

    public static void main2(String[] args) {

        test(new UF_QU_S_PH(count));

        GenericUnionFind<Student> uf = new GenericUnionFind<>();
        Student stu1 = new Student(1, "jack");
        Student stu2 = new Student(2, "rose");
        Student stu3 = new Student(3, "james");
        Student stu4 = new Student(4, "durant");
        uf.makeSet(stu1);
        uf.makeSet(stu2);
        uf.makeSet(stu3);
        uf.makeSet(stu4);

        uf.union(stu1, stu2);
        uf.union(stu3, stu4);
        uf.union(stu1, stu4);

        Asserts.test(uf.isSame(stu1, stu2));
        Asserts.test(uf.isSame(stu3, stu4));
        Asserts.test(uf.isSame(stu1, stu3));
        Asserts.test(uf.isSame(stu2, stu4));
    }

    public static void main(String[] args) {
//        testTime1(new UnionFind_QuickFind(count));
//        testTime1(new UnionFind_QuickUnion(count));
//        testTime1(new UnionFind_QuickUnion_Size(count));
//        testTime1(new UnionFind_QuickUnion_Rank(count));
//        testTime1(new UnionFind_QU_R_PathCompression(count));
//        testTime1(new UnionFind_QU_R_PathSpliting(count));
        testTime1(new UnionFind_QU_R_PathHalving(count));
        testTime3(new GenericUnionFind2<>());
        testTime2(new GenericUnionFind<>());
    }

    static void testTime3(GenericUnionFind2<Integer> uf) {

        for (int i = 0; i < count; i++) {
            uf.makeSet(i);
        }

        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        Asserts.test(!uf.isSame(2, 7));

        uf.union(4, 6);

        Asserts.test(uf.isSame(2, 7));

        Times.test(uf.getClass().getSimpleName(), () -> {
            for (int i = 0; i < count; i++) {
                uf.union((int) (Math.random() * count),
                        (int) (Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int) (Math.random() * count),
                        (int) (Math.random() * count));
            }
        });
    }

    static void testTime2(GenericUnionFind<Integer> uf) {

        for (int i = 0; i < count; i++) {
            uf.makeSet(i);
        }

        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        Asserts.test(!uf.isSame(2, 7));

        uf.union(4, 6);

        Asserts.test(uf.isSame(2, 7));

        Times.test(uf.getClass().getSimpleName(), () -> {
            for (int i = 0; i < count; i++) {
                uf.union((int) (Math.random() * count),
                        (int) (Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int) (Math.random() * count),
                        (int) (Math.random() * count));
            }
        });
    }

    static void testTime1(UF uf) {
        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        Asserts.test(!uf.isSame(2, 7));

        uf.union(4, 6);

        Asserts.test(uf.isSame(2, 7));

        Times.test(uf.getClass().getSimpleName(), () -> {
            for (int i = 0; i < count; i++) {
                uf.union((int) (Math.random() * count),
                        (int) (Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int) (Math.random() * count),
                        (int) (Math.random() * count));
            }
        });
    }

    public static void test(UF_QU_S_PH uf) {
//    public static void test(UF uf) {
        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        Asserts.test(!uf.isSame(0, 6));
        Asserts.test(uf.isSame(0, 5));
        Asserts.test(!uf.isSame(2, 7));

        uf.union(4, 6);

        Asserts.test(uf.isSame(2, 7));
        Asserts.test(uf.isSame(0, 6));
    }
}
