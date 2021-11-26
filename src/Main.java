import taught_by_mjlmj.stepone.heap.BinaryHeap;
import taught_by_mjlmj.stepone.interfaces.Map;
import taught_by_mjlmj.stepone.interfaces.Set;
import taught_by_mjlmj.stepone.map.HashMap_v0_simple;
import taught_by_mjlmj.stepone.map.TreeMap;
import taught_by_mjlmj.stepone.model.*;
import taught_by_mjlmj.stepone.other.Asserts;
import taught_by_mjlmj.stepone.other.FileInfo;
import taught_by_mjlmj.stepone.other.Files;
import taught_by_mjlmj.stepone.other.Times;
import taught_by_mjlmj.stepone.other.printer.BinaryTrees;
import taught_by_mjlmj.stepone.queue.PriorityQueue;
import taught_by_mjlmj.stepone.set.HashSet;
import taught_by_mjlmj.stepone.set.TreeSet1;
import taught_by_mjlmj.stepone.set.TreeSet2;
import taught_by_mjlmj.stepone.tree.AVLTree;
import taught_by_mjlmj.stepone.tree.RedBlackTree;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        testPriorityQueue();
    }

    static void testPriorityQueue() {
        PriorityQueue<Human> queue = new PriorityQueue<>();
        queue.enQueue(new Human("Jack", 2));
        queue.enQueue(new Human("Rose", 10));
        queue.enQueue(new Human("Jake", 5));
        queue.enQueue(new Human("James", 15));
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

    private static void topK() {
        // 找出最大的前k个数
        int k = 3;
        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
                91, 19, 54, 47, 73, 62, 76, 63, 35, 18, 89,
                90, 6, 65, 49, 3, 26, 61, 21, 48};
        BinaryHeap<Integer> minHeap = new BinaryHeap<>(new Comparator<Integer>() {
            /* 返回值大于0，o1比较大 */
            /* 返回值==0，o1==o2 */
            /* 返回值小于0，o2比较大 */
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < data.length; i++) {
            final Integer val = data[i];
            if (minHeap.size() < k) minHeap.add(val);
            else {
                if (val > minHeap.get()) {
                    minHeap.replace(val);
                }
            }
        }
        BinaryTrees.println(minHeap);
    }

    private static void testHeapifyHeap() {
        Integer[] data = {319, 6, 66, 69, 2, 64, 88, 38, 14, 37, 90, 54, 74, 92, 32, 83, 71};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data);
        System.out.println(heap.size());
        BinaryTrees.println(heap);
    }

    private static void testRemoveHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(38);
        heap.add(40);
        heap.add(21);
        heap.add(55);
        heap.add(1);
        heap.add(59);
        heap.add(44);
        heap.add(74);
        heap.add(12);
        heap.add(29);
        heap.add(-3);
        heap.add(545);
        while (!heap.isEmpty()) {
            printHeap(heap);
            heap.remove();
        }
        System.out.println(heap.size());
    }

    private static void testReplaceHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(38);
        heap.add(40);
        heap.add(21);
        heap.add(55);
        heap.add(59);
        heap.add(44);
        heap.add(74);
        heap.add(12);
        heap.add(29);
        heap.add(545);
        for (int i = heap.size() - 1; i >= 0; i--) {
            heap.replace(i);
        }
        printHeap(heap);
        for (int i = 80; i < 100; i++) {
            heap.add(i);
        }
        printHeap(heap);
    }

    private static void printHeap(BinaryHeap<Integer> heap) {
        System.out.println("size : " + heap.size());
        BinaryTrees.println(heap);
        System.out.println("---------------------------------------------");
    }

    static void test() {
        FileInfo fileInfo = Files.read("C:\\Users\\lpruoyu\\Desktop\\java\\util",
                new String[]{"java"});
        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("未去重单词数量：" + words.length);

//        Times.test("ListSet", new Times.Task() {
//            public void execute() {
//                testSet(new ListSet<>(), words);
//            }
//        });

        Times.test("TreeSet", new Times.Task() {
            public void execute() {
                testSet(new TreeSet2<>(), words);
            }
        });


        Times.test("HashSet", new Times.Task() {
            public void execute() {
                testSet(new HashSet<>(), words);
            }
        });

    }

    public static void test5(HashMap_v0_simple<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        Asserts.test(map.get(new SubKey1(1)) == 5);
        Asserts.test(map.get(new SubKey2(1)) == 5);
        Asserts.test(map.size() == 20);
    }

    private static void testHash5() {
        int length = 20;
        HashMap_v0_simple<Object, Integer> map = new HashMap_v0_simple<>();
        for (int i = 1; i < length; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 1; i < length; i++) {
            map.put(new Key(i), i + 10);
        }
        for (int i = 1; i < length; i++) {
            map.put(new Key(i), i + 100);
        }
        System.out.println("map size : " + map.size());
        map.print();
        System.out.println("====================================");
        for (int i = 1; i < length; i++) {
            System.out.print(map.get(new Key(i)) + " ");
        }
    }

    private static void testHash4() {
    /*
    1163157884
    ,460141958
    ,1956725890
    ,356573597
    ,1735600054
    ,21685669
    ,2133927002
    ,1836019240
    ,325040804
    ,1173230247
    ,856419764
    ,621009875
    ,1265094477
    ,2125039532
    ,312714112
    ,692404036
    ,1554874502
    ,1846274136
    ,1639705018
    */
        int length = 20;
        HashMap_v0_simple<Object, Integer> map = new HashMap_v0_simple<>();
        for (int i = 1; i < length; i++) {
            final Key key = new Key(i);
            map.put(key, i);
        }
//        for (Object o : map.hashMap.keySet()) {
//            System.out.println(o + "_" + map.hashMap.get(o));
//        }
        map.print();
        System.out.println("map size : " + map.size());
        for (int i = 1; i < length; i++) {
            System.out.print(map.get(new Key(i)) + " ");
        }
    }

    private static void testHash3() {
        HashMap_v0_simple<Object, Integer> map = new HashMap_v0_simple<>();
        for (int i = 1; i < 20; i++) {
            map.put(new Key(i), i);
        }
        System.out.println("map size : " + map.size());
        map.print();
//        for (int i = 1; i < 20; i++) {
//            System.out.print(map.get(new Key(i)) + " ");
//        }
    }

    private static void testHash2() {
        java.util.Map<Object, Integer> map = new java.util.HashMap<>();
        for (int i = 1; i < 20; i++) {
            map.put(new Key(i), i);
        }
        System.out.println("map size : " + map.size());
        for (int i = 1; i < 20; i++) {
            System.out.print(map.get(new Key(i)) + " ");
        }
    }

    private static void testHash1() {
        Person p1 = new Person(18, 1.6f, "jack");
        Person p2 = new Person(18, 1.6f, "jack");
        Map<Object, Integer> map = new HashMap_v0_simple<>();
//        map.put(p2, 100);
//        map.put(p1, 10);
//        map.put(null, 0);
//        map.put("rose", 10);
//        map.put("jack", 34);
//        map.put("jack", 18);
//        map.traversal(new Map.Visitor<Object, Integer>() {
//            @Override
//            public boolean visit(Object key, Integer value) {
//                System.out.println(key + "_" + value);
//                return false;
//            }
//        });
//        System.out.println("---------------");
        System.out.println(map.containsValue(18));
        map.remove("jack");
        System.out.println(map.containsValue(18));
//        System.out.println(map.containsKey("jack"));
//        System.out.println(map.containsKey(null));
//        System.out.println(map.containsKey("avl red-black"));
//        System.out.println("---------------");
//        System.out.println(map.containsValue(10));
//        System.out.println(map.containsValue(100));
//        System.out.println(map.containsValue(4));
//        System.out.println(map.containsValue(18));
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

    private static void test7() {
        //  TreeSet的限制：必须添加可比较元素
        TreeSet1<Person> redBlackTree = new TreeSet1<>();
        redBlackTree.add(new Person());
        redBlackTree.add(new Person());
    }

    private static void testSet() {
        Set<Integer> set = new TreeSet2<>();
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

//    private static void test5() {
//        final RedBlackTree<Integer> rbTree = new RedBlackTree<>();
//        for (int i = 0; i < arr.length; i++) {
//            rbTree.add(arr[i]);
//            BinaryTrees.println(rbTree);
//            System.out.println("-------------添加---------------");
//        }
//        for (int i = 0; i < arr.length; i++) {
//            rbTree.remove(arr[i]);
//            System.out.println("-------------删除----------------");
//            BinaryTrees.println(rbTree);
//        }
//    }

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
