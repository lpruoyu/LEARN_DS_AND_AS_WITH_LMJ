package taught_by_mjlmj.steptwo.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// QuickUnion + 基于size + PathHalving
// 实现并查集（UnionFind）
public class GenericUnionFind<V> {
    private final Map<V, Node<V>> nodes = new HashMap<>();

    // 初始化每一个元素V为一个单独的集合
    public void makeSet(V v) {
        if (nodes.containsKey(v)) return;
        nodes.put(v, new Node<>(v));
    }

    private static class Node<V> {
        V value;
        Node<V> parent;
        int size; // 基于size的优化

        Node(V value) {
            this.value = value;
            parent = this; // 初始化时，每一个元素的父节点都是自己
            size = 1; // 初始化时，每一个元素单独成为一颗树，树的元素个数肯定为1
        }
    }

    private Node<V> findRootNode(V v) {
        Node<V> vNode = nodes.get(v);
        if (vNode == null) return null;
        while (!Objects.equals(vNode.value, vNode.parent.value)) {
            vNode.parent = vNode.parent.parent;
            vNode = vNode.parent;
        }
        return vNode;
    }

    public V find(V v) {
        final Node<V> rootNode = findRootNode(v);
        return rootNode == null ? null : rootNode.value;
    }

    public void union(V v1, V v2) {
        final Node<V> v1RootNode = findRootNode(v1);
        final Node<V> v2RootNode = findRootNode(v2);
        if (v1RootNode == null || v2RootNode == null) return;
        if (Objects.equals(v1RootNode.value, v2RootNode.value)) return;
        if (v1RootNode.size < v2RootNode.size) {
            v1RootNode.parent = v2RootNode;
            v2RootNode.size += v1RootNode.size;
        } else {
            v2RootNode.parent = v1RootNode;
            v1RootNode.size += v2RootNode.size;
        }
    }

    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }
}
