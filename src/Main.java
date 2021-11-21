import taught_by_mjlmj.stepone.other.printer.BinaryTrees;
import taught_by_mjlmj.stepone.tree.AVLTree;
import taught_by_mjlmj.stepone.tree.RedBlackTree;

public class Main {

    public static void main(String[] args) {
        test5();
    }

    static final int arr[] = {
            74, 71, 52, 97, 3, 49, 73, 24, 62, 38, 65, 13, 75, 55, 9, 66, 86, 68
    };

    private static void test2() {
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < arr.length; i++) {
            avl.add(arr[i]);
            BinaryTrees.println(avl);
            System.out.println("-------------添加---------------");
        }
        for (int i = 0; i < arr.length; i++) {
            avl.remove(arr[i]);
            System.out.println("-------------删除----------------");
            BinaryTrees.println(avl);
        }
    }

    private static void test5() {
        final RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        for (int i = 0; i < arr.length; i++) {
            rbTree.add(arr[i]);
            BinaryTrees.println(rbTree);
            System.out.println("-------------添加---------------");
        }
        for (int i = 0; i < arr.length; i++) {
            rbTree.remove(arr[i]);
            System.out.println("-------------删除----------------");
            BinaryTrees.println(rbTree);
        }
    }

//    private static void test4() {
//        File file = new File("");
//        handle(file);
//    }
//
//    private static void handle(File file) {
//        if (null == file || !file.exists()) return;
//
//        if (file.isDirectory()) {
//            final File[] files = file.listFiles();
//            for (File f : files) {
//                handle(f);
//            }
//        } else {
//            if (!file.getName().endsWith("mp4")) {
//                System.out.println(file.getName() + "删除：" + (file.delete() ? "成功！" : "失败！"));
//            }
//        }
//    }

    private static void test3() {
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        int arr[] = {
                35, 23, 18, 10, 5, 21, 20, 25, 27, 28, 37, 48, 47
        };
        for (int i = 0; i < arr.length; i++) {
            rbTree.add(arr[i]);
        }
        BinaryTrees.println(rbTree);
    }

//    static void test1() {
//        Integer data[] = new Integer[]{
//                67, 52, 92, 96, 53, 95, 13, 63, 34
//        };
//
////        System.out.println("-----------------------begin add----------------");
//
//        AVLTree<Integer> avl = new AVLTree<>();
//        for (int i = 0; i < data.length; i++) {
//            avl.add(data[i]);
////            System.out.println("【" + data[i] + "】");
////            System.out.println("---------------------------------------");
//        }
//
////        BinaryTrees.println(avl);
//
////        System.out.println("-----------------------begin remove----------------");
//
////        for (int i = 0; i < data.length; i++) {
////            avl.remove(data[i]);
////            System.out.println("\n\n---------------------------------------");
////            BinaryTrees.println(avl);
////        }
//
//    }

}
