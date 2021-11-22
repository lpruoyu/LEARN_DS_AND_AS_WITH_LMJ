import taught_by_mjlmj.stepone.map.Map;
import taught_by_mjlmj.stepone.map.TreeMap;
import taught_by_mjlmj.stepone.other.FileInfo;
import taught_by_mjlmj.stepone.other.Files;
import taught_by_mjlmj.stepone.other.Times;
import taught_by_mjlmj.stepone.other.printer.BinaryTrees;
import taught_by_mjlmj.stepone.set.ListSet;
import taught_by_mjlmj.stepone.set.Set;
import taught_by_mjlmj.stepone.set.TreeSet1;
import taught_by_mjlmj.stepone.set.TreeSet;
import taught_by_mjlmj.stepone.tree.AVLTree;
import taught_by_mjlmj.stepone.tree.RedBlackTree;

public class Main {

    public static void main(String[] args) {
        testSet();
    }

    private static void test9() {
        FileInfo fileInfo = Files.read("D:\\code_space\\LearnJava\\IntellijIdea\\LearnDataStructuresWithLMJ\\src\\taught_by_mjlmj\\stepone",
                new String[]{"java"});
        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("未去重单词数量：" + words.length);


        TreeMap<String, Integer> treeMap = new TreeMap<>();

        for (String word : words) {
            final Integer integer = treeMap.get(word);
            int count = integer == null ? 0 : integer;
            treeMap.put(word, count + 1);
        }

        System.out.println("去重单词数量：" + treeMap.size());

        treeMap.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println("key : " + key + " ; value : " + value);
                return false;
            }
        });

    }

    private static void test8() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        System.out.println(treeMap.put("class", 6));
        System.out.println(treeMap.put("class", 3));
        System.out.println(treeMap.put("static", 4));
        System.out.println(treeMap.put("public", 33));
        System.out.println(treeMap.put("static", 6));
        System.out.println(treeMap.put("class", 3));
        System.out.println(treeMap.put("class", 4));
        System.out.println(treeMap.put("public", 4));

        System.out.println("------------------------");

        treeMap.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println("key : " + key + " ; value : " + value);
                return false;
            }
        });

    }

    static class Person {
    }

    static void testSet(Set<String> set, String[] words) {
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.contains(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.remove(words[i]);
        }
    }

    static void test() {
        FileInfo fileInfo = Files.read("C:\\Users\\lpruoyu\\Desktop\\src\\java\\util",
                new String[]{"java"});
        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("未去重单词数量：" + words.length);

        Times.test("ListSet", new Times.Task() {
            public void execute() {
                testSet(new ListSet<>(), words);
            }
        });

        Times.test("TreeSet", new Times.Task() {
            public void execute() {
                testSet(new TreeSet1<>(), words);
            }
        });
    }


    private static void test7() {
        //  TreeSet的限制：必须添加可比较元素
        TreeSet1<Person> redBlackTree = new TreeSet1<>();
        redBlackTree.add(new Person());
        redBlackTree.add(new Person());
    }

    private static void testSet() {
        Set<Integer> set = new TreeSet<>();
        set.add(13);
        set.add(10);
        set.add(13);
        set.add(10);
        set.add(10);
        set.add(11);

        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
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
