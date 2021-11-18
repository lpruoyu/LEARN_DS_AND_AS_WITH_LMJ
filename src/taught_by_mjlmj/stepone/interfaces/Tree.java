package taught_by_mjlmj.stepone.interfaces;

//二叉搜索树接口

public interface Tree<E> {

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E e);

    void add(E e);

    void remove(E e);

    interface Visitor<E> {
        void visit(E element);
    }

}