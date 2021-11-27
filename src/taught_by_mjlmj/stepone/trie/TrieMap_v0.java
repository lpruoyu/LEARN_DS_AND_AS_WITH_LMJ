package taught_by_mjlmj.stepone.trie;

import taught_by_mjlmj.stepone.interfaces.Trie;
import taught_by_mjlmj.stepone.map.HashMap;

public class TrieMap_v0<V> implements Trie<V> {

    private int size;
    private final Node<V> root = new Node<>();

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root.getChildren().clear();
        size = 0;
    }

    @Override
    public V get(String key) {
        final Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean contains(String key) {
        return node(key) != null;
    }

    @Override
    public boolean startsWith(String prefix) {
        keyCheck(prefix);
        Node<V> node = root;
        for (int i = 0, len = prefix.length(); i < len; i++) {
            char c = prefix.charAt(i);
            Node<V> childNode = node.getChildren().get(c);
            if (childNode == null) return false;
            node = childNode;
        }
        return true;
    }

    @Override
    public V add(String key, V value) {
        keyCheck(key);
        Node<V> node = root;
        for (int i = 0, len = key.length(); i < len; i++) {
            char c = key.charAt(i);
            Node<V> childNode = node.getChildren().get(c);
            if (childNode == null) {
                childNode = new Node<>();
                node.getChildren().put(c, childNode);
            }
            node = childNode;
        }
        if (node.isWord) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        node.isWord = true;
        node.value = value;
        size++;
        return null;
    }

    @Override
    public V remove(String key) {
        return null;
    }

    private Node<V> node(String key) {
        keyCheck(key);
        Node<V> node = root;
        for (int i = 0, len = key.length(); i < len; i++) {
            char c = key.charAt(i);
            Node<V> childNode = node.getChildren().get(c);
            if (childNode == null) return null;
            node = childNode;
        }
        return node.isWord ? node : null;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) throw new IllegalArgumentException("Key must not be empty!");
    }

    private static class Node<V> {
        HashMap<Character, Node<V>> children;
        V value;
        boolean isWord;

        HashMap<Character, Node<V>> getChildren() {
            if (null == children) children = new HashMap<>();
            return children;
        }
    }

}
