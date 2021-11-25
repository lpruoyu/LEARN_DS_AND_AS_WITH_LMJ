package taught_by_mjlmj.stepone.map;

import java.util.Objects;

public class LinkedHashMap<K, V> extends HashMap<K, V> {

    private LinkedNode<K, V> first;
    private LinkedNode<K, V> last;

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }

    @Override
    public boolean containsValue(V value) {
        LinkedNode<K, V> node = first;
        while (null != node) {
            if (Objects.equals(value, node.value)) return true;
            node = node.next;
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (null == visitor) return;

        LinkedNode<K, V> node = first;
        while (null != node) {
            if (visitor.visit(node.key, node.value)) return;
            node = node.next;
        }
    }

    @Override
    protected Node<K, V> createNewNode(K key, V value, Node<K, V> parent) {
        LinkedNode<K, V> node = new LinkedNode(key, value, parent);
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    protected void removeAfter(Node<K, V> willNode, Node<K, V> removedNode) {
        LinkedNode<K, V> linkedWillNode = (LinkedNode<K, V>) willNode;
        LinkedNode<K, V> linkedRemoveNode = (LinkedNode<K, V>) removedNode;
        if (linkedWillNode != linkedRemoveNode) {
            // 交换prev
            LinkedNode<K, V> tmp = linkedWillNode.prev;
            linkedWillNode.prev = linkedRemoveNode.prev;
            linkedRemoveNode.prev = tmp;
            if (linkedWillNode.prev == null) {
                first = linkedWillNode;
            } else {
                linkedWillNode.prev.next = linkedWillNode;
            }
            if (linkedRemoveNode.prev == null) {
                first = linkedRemoveNode;
            } else {
                linkedRemoveNode.prev.next = linkedRemoveNode;
            }

            // 交换next
            tmp = linkedWillNode.next;
            linkedWillNode.next = linkedRemoveNode.next;
            linkedRemoveNode.next = tmp;
            if (linkedWillNode.next == null) {
                last = linkedWillNode;
            } else {
                linkedWillNode.next.prev = linkedWillNode;
            }
            if (linkedRemoveNode.next == null) {
                last = linkedRemoveNode;
            } else {
                linkedRemoveNode.next.prev = linkedRemoveNode;
            }
        }
        LinkedNode<K, V> prev = linkedRemoveNode.prev;
        LinkedNode<K, V> next = linkedRemoveNode.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            last = prev;
        } else {
            last.prev = prev;
        }
    }

    protected class LinkedNode<K, V> extends Node<K, V> {
        LinkedNode<K, V> prev;
        LinkedNode<K, V> next;

        LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }

}
