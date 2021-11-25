package taught_by_mjlmj.stepone.map;

import taught_by_mjlmj.stepone.other.printer.BinaryTreeInfo;
import taught_by_mjlmj.stepone.other.printer.BinaryTrees;

import java.util.*;

// 支持null作为key
public class HashMap_v0_simple<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private int size;
    private Node<K, V>[] table;

    public HashMap_v0_simple() {
        table = new Node[DEFAULT_CAPACITY];
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
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key);
        Node<K, V> root = table[index];
        if (null == root) {
            root = new Node<>(key, value, null);
            table[index] = root;
            size++;
            afterPut(root);
            return null;
        }
        // 哈希冲突，向红黑树中添加元素
        return put(root, key, value);
    }

    @Override
    public V get(K key) {
        final Node<K, V> node = findNode(table[hash(key)], key);
        if (node == null) return null;
        return node.value;
    }

    @Override
    public V remove(K key) {
        final Node<K, V> node = findNode(table[hash(key)], key);
        if (node == null) return null;
        return remove(node);
    }

    @Override
    public boolean containsKey(K key) {
        return findNode(table[hash(key)], key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            if (node == null) continue;
            queue.offer(node);
            do {
                node = queue.poll();
                if (Objects.equals(value, node.value)) return true;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            } while (!queue.isEmpty());
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) return;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            if (node == null) continue;
            queue.offer(node);
            do {
                node = queue.poll();
                if (visitor.visit(node.key, node.value)) return;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            } while (!queue.isEmpty());
        }
    }

    // 必须得比个大小
//    private int compare(K k1, K k2, int h1, int h2) {
//        // 当两个对象equals时，就认定这两个对象是同一个对象，那么它们的hashCode值应该相等才对
//        // 但是当两个对象的hashCode值相等时，这两个对象不一定是equals的
//        int result = h1 - h2;
//        if (result != 0) return result;
//        if (Objects.equals(k1, k2)) return 0;
//        if (null != k1 && null != k2 && k1.getClass() == k2.getClass() && k1 instanceof Comparable) {
//            return ((Comparable) k1).compareTo(k2);
//        }
//        return System.identityHashCode(k1) - System.identityHashCode(k2);
//    }

    private V put(Node<K, V> root, K key, V value) {
        Node<K, V> node = root;
        Node<K, V> parent = root;
        Node<K, V> result;
        int h1 = (key == null ? 0 : key.hashCode());
        K k1 = key;
        int cmp;
        boolean searched = false;
        do {
            parent = node;
            int h2 = node.hashCode;
            K k2 = node.key;
            if (Objects.equals(k1, k2)) cmp = 0;
            else if (searched) { // 已经搜索过
                cmp = 1; // 直接往右走
            } else {
                if (
                        (node.left != null && (result = findNode(node.left, k1)) != null) ||
                                (node.right != null && (result = findNode(node.right, k1)) != null)
                ) { // 找到了
                    cmp = 0;
                    node = result;
                } else { // 不存在，没找到
                    searched = true;
                    cmp = 1; // 同样直接往右走
                }
            }

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
                node.hashCode = h1;
                return oldValue;
            }
        } while (null != node);
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else { // cmp < 0
            parent.left = newNode;
        }
        size++;
        afterPut(newNode);
        return null;
    }

    private Node<K, V> findNode(Node<K, V> node, K key) {
        int h1 = (key == null ? 0 : key.hashCode());
        K k1 = key;
        Node<K, V> result;
        while (node != null) {
            int h2 = node.hashCode;
            K k2 = node.key;
            if (Objects.equals(k1, k2)) return node;
            else if (node.left != null && (result = findNode(node.left, k1)) != null) {
                return result;
            } else { // 左边找不到，只能往右边找
                node = node.right;
            }
        }
        return null;
    }

    // 通过hash函数计算索引
    private int hash(K key) {
        if (key == null) return 0;
        int hashCode = key.hashCode();
        hashCode = (hashCode ^ (hashCode >>> 16));
        return hashCode & (table.length - 1);
    }

    // 通过hash函数计算索引
    private int hash(Node<K, V> node) {
        int hashCode = node.hashCode;
        hashCode = (hashCode ^ (hashCode >>> 16));
        return hashCode & (table.length - 1);
    }

    private V remove(Node<K, V> node) {
        V oldValue = node.value;
        if (node.hasTwoChildren()) {
            Node<K, V> s = successor(node);
            node.key = s.key;
            node.value = s.value;
            node.hashCode = s.hashCode;
            node = s;
        }
        int index = hash(node);
        Node<K, V> replacement = node.left == null ? node.right : node.left;
        if (replacement == null) { // 叶子节点
            if (node.parent == null) {
                table[index] = null;
            } else if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        } else { // 度为1的节点
            replacement.parent = node.parent;
            if (node.parent == null) {
                table[index] = replacement;
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
            table[hash(g)] = p;
        }
        p.parent = g.parent;
        g.parent = p;
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
        boolean color;
        int hashCode;
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        Node(K key, V value, Node<K, V> parent) {
            this.hashCode = (key == null ? 0 : key.hashCode());
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

        @Override
        public String toString() {
            String red = (color == RED ? "R" : "");
            return red + "{" + key + "_" + value + "}";
        }

    }

    public void print() {
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            final Node<K, V> root = table[i];
            System.out.println("【index = " + i + "】");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object string(Object node) {
                    return node;
                }

                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>) node).right;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>) node).left;
                }
            });
            System.out.println("---------------------------------------------------");
        }
    }

}
