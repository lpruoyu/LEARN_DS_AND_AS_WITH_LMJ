package taught_by_mjlmj.stepone.interfaces;

public interface Trie<V> {

    int size();

    boolean isEmpty();

    void clear();

    V add(String key, V value); // 添加一个单词

    V remove(String key); // 删除这个单词

    V get(String key); // 拿到这个单词对应的value

    boolean contains(String key); // 判断Trie中是否有这个单词

    boolean startsWith(String prefix); // 判断Trie中是否有以这个单词为前缀的单词

}