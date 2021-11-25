import taught_by_mjlmj.stepone.map.HashMap;
import taught_by_mjlmj.stepone.map.LinkedHashMap;
import taught_by_mjlmj.stepone.map.Map;
import taught_by_mjlmj.stepone.model.Key;
import taught_by_mjlmj.stepone.model.SubKey1;
import taught_by_mjlmj.stepone.model.SubKey2;
import taught_by_mjlmj.stepone.other.Asserts;
import taught_by_mjlmj.stepone.other.FileInfo;
import taught_by_mjlmj.stepone.other.Files;
import taught_by_mjlmj.stepone.other.Times;

public class HashMapMain {

    public static void main(String[] args) {
        testHashMap();
    }

    private static void testHashMap() {
        String filepath = "C:\\Users\\lpruoyu\\Desktop\\java\\util";
        FileInfo fileInfo = Files.read(filepath, null);
        String[] words = fileInfo.words();

        System.out.println("总行数：" + fileInfo.getLines());
        System.out.println("未去重单词总数：" + words.length);
        System.out.println("=============================================");

//        test1Map(map, words);
//        test2(new HashMap_v0_simple<>());
//        test3(new HashMap_v0_simple<>());
//        test4(new HashMap_v0_simple<>());
//        test5(new HashMap_v0_simple<>());

//        test1Map(new HashMap_v0<>(), words);
//        test2(new HashMap_v0<>());
//        test3(new HashMap_v0<>());
//        test4(new HashMap_v0<>());
//        test5(new HashMap_v0<>());
//
        test1Map(new HashMap<>(), words);
        test2(new HashMap<>());
        test3(new HashMap<>());
        test4(new HashMap<>());
        test5(new HashMap<>());

        test1Map(new LinkedHashMap<>(), words);
        test2(new LinkedHashMap<>());
        test3(new LinkedHashMap<>());
        test4(new LinkedHashMap<>());
        test5(new LinkedHashMap<>());
    }

    static void test1Map(Map<String, Integer> map, String[] words) {
        Times.test(map.getClass().getName(), new Times.Task() {
            @Override
            public void execute() {
                for (String word : words) {
                    Integer count = map.get(word);
                    count = count == null ? 0 : count;
                    map.put(word, count + 1);
                }

                System.out.println("去重单词总数：" + map.size());

                int count = 0;
                for (String word : words) {
                    Integer i = map.get(word);
                    count += i == null ? 0 : i;
                    map.remove(word);
                }

                Asserts.test(count == words.length);
                Asserts.test(map.size() == 0);
            }
        });
    }

    static void test2(Map<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 20);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == 10);
        Asserts.test(map.get(new Key(6)) == 11);
        Asserts.test(map.get(new Key(7)) == 12);
        Asserts.test(map.get(new Key(8)) == 8);
    }

    static void test3(Map<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        Asserts.test(map.size() == 5);
        Asserts.test(map.get(null) == 8);
        Asserts.test(map.get("jack") == 6);
        Asserts.test(map.get(10) == null);
        Asserts.test(map.get(new Object()) == null);
        Asserts.test(map.containsKey(10));
        Asserts.test(map.containsKey(null));
        Asserts.test(map.containsValue(null));
        Asserts.test(map.containsValue(1) == false);
    }

    static void test4(Map<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            Asserts.test(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 19);
        Asserts.test(map.get(new Key(1)) == 6);
        Asserts.test(map.get(new Key(2)) == 7);
        Asserts.test(map.get(new Key(3)) == 8);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == null);
        Asserts.test(map.get(new Key(6)) == null);
        Asserts.test(map.get(new Key(7)) == null);
        Asserts.test(map.get(new Key(8)) == 8);
        map.traversal(new Map.Visitor<Object, Integer>() {
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test5(Map<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        Asserts.test(map.get(new SubKey1(1)) == 5);
        Asserts.test(map.get(new SubKey2(1)) == 5);
        Asserts.test(map.size() == 20);
    }

}
