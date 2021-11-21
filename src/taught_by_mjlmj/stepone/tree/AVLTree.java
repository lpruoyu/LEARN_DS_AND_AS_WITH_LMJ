package taught_by_mjlmj.stepone.tree;

import java.util.Comparator;

public class AVLTree<E> extends BalancedBinarySearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 如果平衡更新高度
                updateHeight(node);
            } else { // 如果不平衡 恢复平衡
                reBalance(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) { // 如果平衡更新高度
                updateHeight(node);
            } else { // 如果不平衡 恢复平衡
                reBalance(node);
            }
        }
    }

    private void reBalance2(Node<E> grand) {
        AVLNode<E> g = (AVLNode<E>) grand;
        AVLNode<E> p = g.tallerSubAVLNode();
        AVLNode<E> n = p.tallerSubAVLNode();

        if (p.isLeftOfParent()) { // L
            if (n.isLeftOfParent()) { // LL
                rotate(g, n.left, n, n.right, p, p.right, g, g.right);
            } else { // LR
                rotate(g, p.left, p, n.left, n, n.right, g, g.right);
            }
        } else { // R
            if (n.isLeftOfParent()) { // RL
                rotate(g, g.left, g, n.left, n, n.right, p, p.right);
            } else { // RR
                rotate(g, g.left, g, p.left, p, n.left, n, n.right);
            }
        }
    }


    private void reBalance(Node<E> grand) {
        AVLNode<E> g = (AVLNode<E>) grand;
        AVLNode<E> p = g.tallerSubAVLNode();
        AVLNode<E> n = p.tallerSubAVLNode();

        if (p.isLeftOfParent()) { // L
            if (n.isLeftOfParent()) { // LL
                rotateRight(g);
            } else { // LR
                rotateLeft(p);
                rotateRight(g);
            }
        } else { // R
            if (n.isLeftOfParent()) { // RL
                rotateRight(p);
                rotateLeft(g);
            } else { // RR
                rotateLeft(g);
            }
        }

    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    @Override
    protected void afterRotate(Node<E> g, Node<E> p) {
        super.afterRotate(g, p);
        updateHeight(g);
        updateHeight(p);
    }

    @Override
    protected Node<E> createNewNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private boolean isBalanced(Node<E> node) {
        return ((AVLNode<E>) node).isBalanced();
//        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    private static class AVLNode<E> extends Node<E> {

        // 叶子节点的高度默认为1
        int height = 1;

        AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        int leftSubTreeHeight() {
            int l = 0;
            if (left != null) l = ((AVLNode<E>) left).height;
            return l;
        }

        int rightSubTreeHeight() {
            int r = 0;
            if (right != null) r = ((AVLNode<E>) right).height;
            return r;
        }

        int balanceFactor() {
            return leftSubTreeHeight() - rightSubTreeHeight();
        }

        void updateHeight() {
            height = Math.max(leftSubTreeHeight(), rightSubTreeHeight()) + 1;
        }

        boolean isBalanced() {
            return Math.abs(balanceFactor()) <= 1;
        }

        // 返回左右子树较高的节点
        AVLNode<E> tallerSubAVLNode() {
            if (leftSubTreeHeight() > rightSubTreeHeight()) return (AVLNode<E>) left;
            else if (leftSubTreeHeight() < rightSubTreeHeight()) return (AVLNode<E>) right;
            else {
                if (isLeftOfParent()) return (AVLNode<E>) left;
                else return (AVLNode<E>) right;
            }
        }

        @Override
        public String toString() {
            String p = parent != null ? parent.element.toString() : "null";
            return p + "_" + element;
//            return height + "_" + element;
        }

        //        int balanceFactor() {
//            int l = 0;
//            int r = 0;
//            if (left != null) l = ((AVLNode<E>) left).height;
//            if (right != null) r = ((AVLNode<E>) right).height;
//            return l - r;
//        }
//
//        void updateHeight() {
//            int l = 0;
//            int r = 0;
//            if (left != null) l = ((AVLNode<E>) left).height;
//            if (right != null) r = ((AVLNode<E>) right).height;
//
//            height = Math.max(l, r) + 1;
//        }
//
//        boolean isBalanced() {
//            int l = 0;
//            int r = 0;
//            if (left != null) l = ((AVLNode<E>) left).height;
//            if (right != null) r = ((AVLNode<E>) right).height;
//
//            return Math.abs(l - r) <= 1;
//        }

    }

    @Override
    public Object string(Object node) {
        return node;
    }

}
