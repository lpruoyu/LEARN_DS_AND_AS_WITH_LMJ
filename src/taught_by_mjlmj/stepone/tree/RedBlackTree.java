package taught_by_mjlmj.stepone.tree;

import java.util.Comparator;

public class RedBlackTree<E> extends BalancedBinarySearchTree<E> {

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        /* 注意：B树中，真正被删除的节点都是叶子节点 */
        if (isRed(node)) return; // 删除的是红色节点，不用做任何处理
        if (isRed(replacement)) { // 用以替代的子节点是红色节点
            black(replacement);
            return;
        }

        /* 其余情况（删除的是黑色叶子节点） */

        // 删除的是根节点 或者 下溢到了根节点
        if (node.parent == null) return;

        Node<E> parent = node.parent;

        // 获取兄弟节点
        // 叶子节点删除时如果是左孩子，会执行node.parent.left = null;
        boolean isLeftChild = parent.left == null || node.isLeftOfParent();
        Node<E> sibling = isLeftChild ? parent.right : parent.left;

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

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        if (parent == null) { // 添加的是根节点 或者 上溢到了根节点
            black(node);
            return;
        }

        if (isBlack(parent)) { // parent为BLACK 不用做任何处理 4种情况
            return;
        }

        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;
        if (isRed(uncle)) { // 会上溢 4种情况
            black(parent);
            black(uncle);
            red(grand);
            afterAdd(grand);
            return;
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

    @Override
    protected Node<E> createNewNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    // 给节点上色（染色）
    private RBNode<E> color(Node<E> node, boolean color) {
        if (null == node) return null;
        final RBNode<E> rbNode = (RBNode<E>) node;
        rbNode.color = color;
        return rbNode;
    }

    // 将节点染为黑色
    private RBNode<E> black(Node<E> node) {
        return color(node, RBNode.BLACK);
    }

    // 将节点染为黑色
    private RBNode<E> red(Node<E> node) {
        return color(node, RBNode.RED);
    }

    // 判断该节点是什么颜色
    private boolean colorOf(Node<E> node) {
        // 红黑树中的空节点是黑色节点
        return node == null ? RBNode.BLACK : ((RBNode<E>) node).color;
    }

    // 判断节点是否为红色节点
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RBNode.RED;
    }

    // 判断节点是否为黑色节点
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == RBNode.BLACK;
    }

    private static class RBNode<E> extends Node<E> {
        // 建议新添加的节点默认为RED
        private static final boolean RED = false;
        private static final boolean BLACK = true;

        // 建议新添加的节点默认为RED
        private boolean color;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String colorStr = "";
            if (color == RED) {
                colorStr = "R_";
            }
            return colorStr + element;
        }

    }

}
