package taught_by_mjlmj.stepone.tree;

// 二叉树

import taught_by_mjlmj.stepone.interfaces.Queue;
import taught_by_mjlmj.stepone.interfaces.Stack;
import taught_by_mjlmj.stepone.interfaces.Tree;
import taught_by_mjlmj.tools.printer.BinaryTreeInfo;
import taught_by_mjlmj.stepone.queue.LinkedListQueue;
import taught_by_mjlmj.stepone.stack.LinkedListStack;

public abstract class BinaryTree<E> implements Tree<E>, BinaryTreeInfo {

    protected Node<E> root;
    protected int size;

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

    // 层序遍历
    public void leverOrderTraversal(BinarySearchTree.Visitor<E> visitor) {
        traversalCheck(visitor);
        Queue<Node<E>> queue = new LinkedListQueue<>();
        queue.enQueue(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.deQueue();
            visitor.visit(node.element);
            if (node.left != null) queue.enQueue(node.left);
            if (node.right != null) queue.enQueue(node.right);
        }
    }

    // 非递归前序遍历
    public void preorderTraversal(BinarySearchTree.Visitor<E> visitor) {
        if (visitor == null || root == null) return;
        Stack<Node<E>> stack = new LinkedListStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            visitor.visit(node.element);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    // 非递归前序遍历
    public void preorderTraversal1(BinarySearchTree.Visitor<E> visitor) {
        if (visitor == null) return;
        Stack<Node<E>> stack = new LinkedListStack<>();
        Node<E> node = root;
        while (true) {
            if (node != null) {
                visitor.visit(node.element);
                // 如果有右子节点，就将右子节点入栈
                if (node.right != null) stack.push(node.right);
                node = node.left; // 一路往左走
            } else {
                // node为null时，准备访问先前节点的右子节点
                if (!stack.isEmpty()) {
                    // 让拿出的节点继续执行 node != null 时的操作
                    node = stack.pop();
                } else {
                    return;
                }
            }
        }
    }

    //    递归前序遍历
    private void preorderTraversal(BinarySearchTree.Node<E> node, BinarySearchTree.Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        visitor.visit(node.element);

        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    // 非递归后序遍历
    public void postorderTraversal(BinarySearchTree.Visitor<E> visitor) {
        if (null == visitor || root == null) return;
        Node<E> prev = null;
        Stack<Node<E>> stack = new LinkedListStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> node = stack.top();
            if (node.isLeaf() || (prev != null && prev.parent == node)) {
                Node<E> leaf = stack.pop();
                visitor.visit(leaf.element);
                prev = leaf;
            } else {
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }
    }

    // 递归后序遍历
    private void postorderTraversal(BinarySearchTree.Node<E> node, BinarySearchTree.Visitor<E> visitor) {
        if (node == null) return;

        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);

        visitor.visit(node.element);
    }

    // 非递归中序遍历
    public void inorderTraversal(BinarySearchTree.Visitor<E> visitor) {
        if (null == visitor) return;
        Stack<Node<E>> stack = new LinkedListStack<>();
        Node<E> node = root;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (!stack.isEmpty()) {
                    node = stack.pop();
                    visitor.visit(node.element);
                    node = node.right;
                } else {
                    return;
                }
            }
        }
    }

    // 递归中序遍历
    private void inorderTraversal(BinarySearchTree.Node<E> node, BinarySearchTree.Visitor<E> visitor) {
        if (node == null) return;

        inorderTraversal(node.left, visitor);

        visitor.visit(node.element);

        inorderTraversal(node.right, visitor);
    }

    private void traversalCheck(Visitor<E> visitor) {
        if (null == root || null == visitor) {
            System.out.println(
                    "BinarySearchTree must not be empty and Visitor must not be null!");
        }
    }

    protected void elementNotNullCheck(E element) {
        if (null == element) {
            throw new IllegalArgumentException("Element must not be null!");
        }
    }

    // 判断是否是完全二叉树
    public boolean isComplete() {
        if (root == null) return true;

        boolean leaf = false;
        Queue<Node<E>> queue = new LinkedListQueue<>();
        queue.enQueue(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.deQueue();

            if (leaf) { // 要求后面的都是叶子结点
                if (!node.isLeaf()) return false;
                continue;
            }

            if (node.hasTwoChildren()) {
                queue.enQueue(node.left);
                queue.enQueue(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                // left != null right == null
                // left == null right == null
                leaf = true;
                if (node.left != null) queue.enQueue(node.left);
            }
        }

        return true;
    }

    public void invertBinaryTree() {
        if (root == null) return;

        final Queue<Node<E>> queue = new LinkedListQueue<>();
        queue.enQueue(root);
        while (!queue.isEmpty()) {
            final Node<E> node = queue.deQueue();

            Node<E> temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) queue.enQueue(node.left);
            if (node.right != null) queue.enQueue(node.right);
        }
    }

    public int height() {
        if (root == null) return 0;

        final Queue<Node<E>> queue = new LinkedListQueue<>();
        queue.enQueue(root);
        int height = 0;
        int levelSize = 1;

        while (!queue.isEmpty()) {

            Node<E> node = queue.deQueue();
            levelSize--;

            if (node.left != null) queue.enQueue(node.left);

            if (node.right != null) queue.enQueue(node.right);

            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }

        }

        return height;

    }

    // 寻找前驱节点
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        Node<E> p = node.left;
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
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;

        Node<E> s = node.right;
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

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        Node(E element, Node<E> parent) {
            this.element = element;
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
        Node<E> sibling() {
            if (isLeftOfParent()) return (Node<E>) parent.right;
            if (isRightOfParent()) return (Node<E>) parent.left;
            return null;
        }

        @Override
        public String toString() {
            return element + "";
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }

//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder();
//        toString(root, sb, "");
//        return sb.toString();
//    }
//
//    private void toString(Node<E> node, StringBuilder sb, String preStr) {
//        if (null == node) return;
//
//        sb.append(preStr).append("【").append(node.element).append("】").append("\n");
//        toString(node.left, sb, preStr + "[L]");
//        toString(node.right, sb, preStr + "[R]");
//    }


//
//    // 层序遍历
//    public void leverOrderTraversal() {
//        if (null == root) {
//            System.out.println("BinarySearchTree does not have any element!");
//            return;
//        }
//        final java.util.Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//        Node<E> node;
//        while (!queue.isEmpty()) {
//            node = queue.poll();
//            System.out.print(node.element + " ");
//            if (node.left != null) queue.offer(node.left);
//            if (node.right != null) queue.offer(node.right);
//        }
//    }
//
//    // 前序遍历
//    public void preorderTraversal() {
//        preorderTraversal(root);
//    }
//
//    private void preorderTraversal(Node<E> node) {
//
//        if (node == null) return;
//
//        System.out.print(node.element + " ");
//
//        preorderTraversal(node.left);
//        preorderTraversal(node.right);
//
//    }
//
//    // 后序遍历
//    public void postorderTraversal() {
//        postorderTraversal(root);
//    }
//
//    private void postorderTraversal(Node<E> node) {
//
//        if (node == null) return;
//
//        postorderTraversal(node.left);
//        postorderTraversal(node.right);
//
//        System.out.print(node.element + " ");
//
//    }
//
//    // 中序遍历
//    public void inorderTraversal() {
//        inorderTraversal(root);
//    }
//
//    private void inorderTraversal(Node<E> node) {
//
//        if (node == null) return;
//
//        inorderTraversal(node.left);
//
//        System.out.print(node.element + " ");
//
//        inorderTraversal(node.right);
//
//    }
//

//使用前序遍历
//public void invertBinaryTree() {
//    invertBinaryTree(root);
//}
//    private void invertBinaryTree(Node<E> node) {
//        if (node == null) return;
//
//        Node<E> temp = node.left;
//        node.left = node.right;
//        node.right = temp;
//
//        invertBinaryTree(node.left);
//        invertBinaryTree(node.right);
//    }


//    递归实现高度计算
//    public int height() {
//        return height(root);
//    }
//
//    private int height(Node<E> node) {
//        if (node == null) return 0;
//
//        return Math.max(height(node.left), height(node.right)) + 1;
//    }


//    private void remove(Node<E> node) {
//        if (null == node) return;
//
//        if (node.hasTwoChildren()) {
//            Node<E> p = predecessor(node);
//            node.element = p.element;
//            node = p;
//        }
//
//        // node要么是叶子结点，要么是度为1的节点
//
//        Node<E> replacement = node.left == null ? node.right : node.left;
//        if (replacement == null) { // 叶子结点
//            if (node.parent == null) {
//                root = null;
//            } else {
//                if (node.parent.left == node) {
//                    node.parent.left = null;
//                } else {
//                    node.parent.right = null;
//                }
//            }
//        } else { // 度为1的节点
//            replacement.parent = node.parent;
//            if (node.parent == null) {
//                root = replacement;
//            } else {
//                if (node.parent.left == node) {
//                    node.parent.left = replacement;
//                } else {
//                    node.parent.right = replacement;
//                }
//            }
//        }
//        size--;
//    }

}