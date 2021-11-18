package taught_by_mjlmj.stepone.interfaces;

public interface Stack <E> {

    int size();

    boolean isEmpty();

    void push(E element);

    E pop();

    // peek 获取栈顶元素
    E top();

    void clear();

}
