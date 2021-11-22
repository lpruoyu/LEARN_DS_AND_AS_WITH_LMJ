package taught_by_mjlmj.stepone.set;

import taught_by_mjlmj.stepone.interfaces.Tree;
import taught_by_mjlmj.stepone.tree.RedBlackTree;

import java.util.Comparator;

public class TreeSet1<E> implements Set<E> {

    private final RedBlackTree<E> tree;

    public TreeSet1() {
        tree = new RedBlackTree<>();
    }

    public TreeSet1(Comparator<E> comparator) {
        tree = new RedBlackTree<>(comparator);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element); // 红黑树内自动会覆盖相等的元素
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inorderTraversal(new Tree.Visitor<E>() { // 中序遍历：从小到大
            @Override
            public void visit(E element) {
                visitor.visit(element);
            }
        });
    }

}
