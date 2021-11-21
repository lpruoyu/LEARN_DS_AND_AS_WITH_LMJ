package taught_by_mjlmj.stepone.tree;

import java.util.Comparator;

public class BalancedBinarySearchTree<E> extends BinarySearchTree<E> {

    public BalancedBinarySearchTree() {
        this(null);
    }

    public BalancedBinarySearchTree(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotate(
            Node<E> r,
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g
    ) {

        if (r.isLeftOfParent()) {
            r.parent.left = d;
        } else if (r.isRightOfParent()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        d.parent = r.parent;

        b.left = a;
        if (a != null) a.parent = b;
        b.right = c;
        if (c != null) c.parent = b;
        f.left = e;
        if (e != null) e.parent = f;
        f.right = g;
        if (g != null) g.parent = f;

        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;

    }

    // 右旋转
    protected void rotateRight(Node<E> g) {
        Node<E> p = (Node<E>) g.left;
        if (p.right != null) p.right.parent = g;
        g.left = p.right;
        p.right = g;
        afterRotate(g, p);
    }

    // 左旋转
    protected void rotateLeft(Node<E> g) {
        Node<E> p = (Node<E>) g.right;
        if (p.left != null) p.left.parent = g;
        g.right = p.left;
        p.left = g;
        afterRotate(g, p);
    }

    protected void afterRotate(Node<E> g, Node<E> p) {
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

}
