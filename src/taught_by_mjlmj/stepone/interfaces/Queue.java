package taught_by_mjlmj.stepone.interfaces;

public interface Queue<E> {

    int size();

    void enQueue(E element);

    E deQueue();

    // 获取队列头元素
    E front();

    void clear();

    boolean isEmpty();

}
