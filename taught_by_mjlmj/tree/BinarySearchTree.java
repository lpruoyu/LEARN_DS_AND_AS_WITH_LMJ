package taught_by_mjlmj.tree;

/*
    二叉搜索树
 */

import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        Node<E> newNode = createNewNode(element, null);
        if (null == root) {
            root = newNode;
        } else {
            Node<E> node = root;
            Node<E> parent = root;
            int cmp = 0;
            while (null != node) {
                parent = node;
                cmp = compare(element, node.element);
                if (cmp > 0) {
                    node = node.right;
                } else if (cmp < 0) {
                    node = node.left;
                } else {
                    /*
                        元素相等时建议更新元素
                    */
                    node.element = element;
                    return;
                }
            }

            newNode = createNewNode(element, parent);

            if (cmp > 0) {
                parent.right = newNode;
            } else { // cmp < 0
                parent.left = newNode;
            }
        }

        size++;

        afterAdd(newNode);
    }

    protected void afterAdd(Node<E> node) {
    }

    protected void afterRemove(Node<E> node) {
    }

    protected Node<E> createNewNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    @Override
    public void remove(E element) {
        remove(findNode(element));
    }

    private void remove(Node<E> node) {
        if (null == node) return;

        if (node.hasTwoChildren()) {
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }

        Node<E> replacement = node.left == null ? node.right : node.left;
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

        afterRemove(node);

    }

    private Node<E> findNode(E element) {
        if (null == element) return null; // 不支持添加null元素

        Node<E> node = root;
        int compare;
        while (node != null) {
            compare = compare(element, node.element);
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

    @Override
    public boolean contains(E e) {
        return findNode(e) != null;
    }

    protected int compare(E e1, E e2) {
        if (null != comparator) {
            return comparator.compare(e1, e2);
        }

        return ((Comparable) e1).compareTo(e2);
    }

}