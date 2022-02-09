package taught_by_mjlmj.steptwo.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// QuickUnion + 基于rank + PathHalving实现UnionFind
public class GenericUnionFind2<V> {
    private final Map<V, Node<V>> nodes = new HashMap<>();

    public void makeSet(V v) {
        if (nodes.containsKey(v)) return;
        nodes.put(v, new Node<>(v));
    }

    private static class Node<V> {
        V value;
        Node<V> parent;
        int rank; // 基于rank的优化

        Node(V value) {
            this.value = value;
            parent = this;
            rank = 1; // 初始化时，每一个元素单独成为一颗树，树的高度肯定为1
        }
    }

    // 基于PathHalving：让路径上每隔一个节点就指向其祖父节点（node.parent.parent）
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
        if (v1RootNode.rank < v2RootNode.rank) {
            v1RootNode.parent = v2RootNode;
        } else if (v1RootNode.rank > v2RootNode.rank) {
            v2RootNode.parent = v1RootNode;
        } else {
            v1RootNode.parent = v2RootNode;
            v2RootNode.rank++;
        }
    }

    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }
}
