package taught_by_mjlmj.stepone.trie;

import taught_by_mjlmj.stepone.interfaces.Trie;
import taught_by_mjlmj.stepone.map.HashMap;

public class TrieMap<V> implements Trie<V> {

    private int size;
    private Node<V> root;

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
        root = null;
        size = 0;
    }

    @Override
    public V get(String key) {
        Node<V> node = node(key);
        if (node != null && node.isWord) return node.value;
        return null;
    }

    @Override
    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.isWord;
    }

    @Override
    public boolean startsWith(String prefix) {
        Node<V> node = node(prefix);
        return node != null;
    }

    @Override
    public V add(String key, V value) {
        keyCheck(key);
        if (null == root) root = new Node<>(null, null);
        Node<V> node = root;
        for (int i = 0, len = key.length(); i < len; i++) {
            char c = key.charAt(i);
            boolean childrenIsEmpty = (node.children == null);
            Node<V> childNode = childrenIsEmpty ? null : node.children.get(c);
            if (childNode == null) {
                childNode = new Node<>(c, node);
                (childrenIsEmpty ? node.children = new HashMap<>() : node.children).put(c, childNode);
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
        Node<V> node = node(key);
        if (null == node || !node.isWord) return null;
        size--;

        V oldValue = node.value;
        if (null != node.children && !node.children.isEmpty()) {
            node.value = null;
            node.isWord = false;
            return oldValue;
        }
        Node<V> parent = node.parent;
        while (parent != null) {
            parent.children.remove(node.character);
            if (!parent.children.isEmpty() || parent.isWord) break;
            parent = parent.parent;
        }
        return oldValue;
    }

    private Node<V> node(String key) {
        keyCheck(key);
        Node<V> node = root;
        for (int i = 0, len = key.length(); i < len; i++) {
            if (node == null || node.children == null || node.children.isEmpty()) return null;
            node = node.children.get(key.charAt(i));
        }
        return node;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) throw new IllegalArgumentException("Key must not be empty!");
    }

    private static class Node<V> {
        HashMap<Character, Node<V>> children;
        V value;
        boolean isWord;
        Node<V> parent;
        Character character;

        Node(Character character, Node<V> parent) {
            this.character = character;
            this.parent = parent;
        }
    }

}
