package taught_by_mjlmj.stepone.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

// TreeMap就是一颗RedBlackTree
public class TreeMap<K, V> implements Map<K, V> {

    private Node<K, V> root;
    private int size;

    private Comparator<K> comparator;

    public TreeMap() {
    }

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

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
    public V put(K key, V value) {

        keyNotNullCheck(key);

        Node<K, V> newNode = new Node<>(key, value, null);

        if (null == root) {
            root = newNode;
        } else {
            Node<K, V> node = root;
            Node<K, V> parent = root;
            int cmp = 0;
            while (null != node) {
                parent = node;
                cmp = compare(key, node.key);
                if (cmp > 0) {
                    node = node.right;
                } else if (cmp < 0) {
                    node = node.left;
                } else {
                    /*
                        元素相等时建议更新元素
                        之前key存在时，更新key和value
                    */
                    V oldValue = node.value;
                    node.key = key;
                    node.value = value;
                    return oldValue;
                }
            }

            newNode = new Node<>(key, value, parent);

            if (cmp > 0) {
                parent.right = newNode;
            } else { // cmp < 0
                parent.left = newNode;
            }
        }

        size++;

        afterPut(newNode);

        return null;
    }

    @Override
    public V get(K key) {
        final Node<K, V> node = findNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        return remove(findNode(key));
    }


    @Override
    public boolean containsKey(K key) {
        return findNode(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) return false;

        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();

            if (valueEquals(value, node.value)) return true;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) { // 暴露给外部最好使用中序遍历
        if (root == null || visitor == null) return;
        traversal(root, visitor);
    }

    private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) return;

        traversal(node.left, visitor);
        if (!visitor.stop) visitor.visit(node.key, node.value);
        traversal(node.right, visitor);
    }

    private boolean valueEquals(V v1, V v2) { // value并不需要具备可比性（比较大小）
        return v1 == null ? v2 == null : v1.equals(v2);
    }

    private V remove(Node<K, V> node) {
        if (null == node) return null;
        V oldValue = node.value;
        if (node.hasTwoChildren()) {
            Node<K, V> s = successor(node);
            node.key = s.key;
            node.value = s.value;
            node = s;
        }

        Node<K, V> replacement = node.left == null ? node.right : node.left;
        if (replacement == null) { // 叶子节点
            if (node.parent == null) {
                root = null;
            } else if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        } else { // 度为1的节点
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node.parent.left == node) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        }

        size--;

        afterRemove(node, replacement);
        return oldValue;
    }

    private void afterRemove(Node<K, V> node, Node<K, V> replacement) {
        /* 注意：B树中，真正被删除的节点都是叶子节点 */
        if (isRed(node)) return; // 删除的是红色节点，不用做任何处理
        if (isRed(replacement)) { // 用以替代的子节点是红色节点
            black(replacement);
            return;
        }

        /* 其余情况（删除的是黑色叶子节点） */

        // 删除的是根节点
        if (node.parent == null) return;

        Node<K, V> parent = node.parent;

        // 获取兄弟节点
        // 叶子节点删除时如果是左孩子，会执行node.parent.left = null;
        boolean isLeftChild = parent.left == null || node.isLeftOfParent();
        Node<K, V> sibling = isLeftChild ? parent.right : parent.left;

        if (isLeftChild) { // 被删除的叶子节点在左边，兄弟节点在右边
            // 与 “被删除的叶子节点在右边，兄弟节点在左边” 是对称的
            // 旋转更新方向、用左边变为用右边、用右边变为用左边
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                sibling = parent.right;
            }
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                boolean isBlackParent = isBlack(parent);
                red(sibling);
                black(parent);
                if (isBlackParent) {
                    afterRemove(parent, null);
                }
            } else {
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.right);
                rotateLeft(parent);
            }
        } else { // 被删除的叶子节点在右边，兄弟节点在左边（老师PPT上画的情况）
            if (isRed(sibling)) { // 判断兄弟节点是否为RED
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更新sibling
                sibling = parent.left;
            }

            // 以下情况，兄弟结点肯定为黑色

            // 判断sibling是否至少有1个RED子节点
            if (isBlack(sibling.left) && isBlack(sibling.right)) { // sibling没有任何RED子节点
                boolean isBlackParent = isBlack(parent);
                red(sibling);
                black(parent);
                if (isBlackParent) {
                    afterRemove(parent, null);
                }
            } else { // sibling至少有一个RED子节点 可以向兄弟借元素
                if (isBlack(sibling.left)) { // 如果兄弟节点的左边是黑色，兄弟先旋转
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                // 先染色，后旋转
                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.left);
                rotateRight(parent);
                //先旋转，后染色
//                rotateRight(parent);
//                color(sibling, colorOf(parent));
//                black(sibling.left);
//                black(sibling.right);
            }

        }

    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        if (parent == null) { // 添加的是根节点 或者 上溢到了根节点
            black(node);
            return;
        }

        if (isBlack(parent)) { // parent为BLACK 不用做任何处理 4种情况
            return;
        }

        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = parent.parent;
        if (isRed(uncle)) { // 会上溢 4种情况
            black(parent);
            black(uncle);
            red(grand);
            afterPut(grand);
        } else { // 4种情况
            if (parent.isLeftOfParent()) { // L
                if (node.isLeftOfParent()) { // LL
                    black(parent);
                    red(grand);
                    rotateRight(grand);
                } else { // LR
                    black(node);
                    red(grand);
                    rotateLeft(parent);
                    rotateRight(grand);
                }
            } else { // R
                if (node.isLeftOfParent()) { // RL
                    black(node);
                    red(grand);
                    rotateRight(parent);
                    rotateLeft(grand);
                } else { // RR
                    black(parent);
                    red(grand);
                    rotateLeft(grand);
                }
            }
        }

    }

    // 寻找前驱节点
    private Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) return null;

        Node<K, V> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        while (node.parent != null && node.parent.right != node) {
            node = node.parent;
        }
        return node.parent;
    }

    // 后继节点
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) return null;

        Node<K, V> s = node.right;
        if (s != null) {
            while (s.left != null) {
                s = s.left;
            }
            return s;
        }

        while (node.parent != null && node != node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    private Node<K, V> findNode(K key) {
        if (null == key) return null;

        Node<K, V> node = root;
        int compare;
        while (node != null) {
            compare = compare(key, node.key);
            if (compare == 0) {
                return node;
            } else if (compare < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    // 右旋转
    private void rotateRight(Node<K, V> g) {
        Node<K, V> p = g.left;
        if (p.right != null) p.right.parent = g;
        g.left = p.right;
        p.right = g;
        afterRotate(g, p);
    }

    // 左旋转
    private void rotateLeft(Node<K, V> g) {
        Node<K, V> p = (Node<K, V>) g.right;
        if (p.left != null) p.left.parent = g;
        g.right = p.left;
        p.left = g;
        afterRotate(g, p);
    }

    private void afterRotate(Node<K, V> g, Node<K, V> p) {
        if (g.parent != null) {
            if (g.isLeftOfParent()) {
                g.parent.left = p;
            } else {
                g.parent.right = p;
            }
        } else {
            root = p;
        }
        p.parent = g.parent;
        g.parent = p;
    }

    private int compare(K k1, K k2) {
        if (null != comparator) {
            return comparator.compare(k1, k2);
        }

        return ((Comparable) k1).compareTo(k2);
    }

    private void keyNotNullCheck(K key) {
        if (null == key) {
            throw new IllegalArgumentException("Key must not be null!");
        }
    }

    // 给节点上色（染色）
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (null == node) return null;
        final Node<K, V> rbNode = node;
        rbNode.color = color;
        return rbNode;
    }

    // 将节点染为黑色
    private Node<K, V> black(Node<K, V> node) {
        return color(node, Node.BLACK);
    }

    // 将节点染为黑色
    private Node<K, V> red(Node<K, V> node) {
        return color(node, Node.RED);
    }

    // 判断该节点是什么颜色
    private boolean colorOf(Node<K, V> node) {
        // 红黑树中的空节点是黑色节点
        return node == null ? Node.BLACK : node.color;
    }

    // 判断节点是否为红色节点
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == Node.RED;
    }

    // 判断节点是否为黑色节点
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == Node.BLACK;
    }

    private static class Node<K, V> {

        // 建议新添加的节点默认为RED
        private static final boolean RED = false;
        private static final boolean BLACK = true;

        // 建议新添加的节点默认为RED
        private boolean color;


        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        boolean isLeftOfParent() {
            return parent != null && parent.left == this;
        }

        boolean isRightOfParent() {
            return parent != null && parent.right == this;
        }

        boolean hasTwoChildren() {
            return left != null && right != null;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        // 返回兄弟节点
        Node<K, V> sibling() {
            if (isLeftOfParent()) return parent.right;
            if (isRightOfParent()) return parent.left;
            return null;
        }

    }

}
