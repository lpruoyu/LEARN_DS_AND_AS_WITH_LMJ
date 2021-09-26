package taught_by_mjlmj.interfaces;

/*double ended queue 双端队列*/

public interface Deque<E> {

    int size();

    // 从队首入队
    void enQueueFront(E element);

    // 从队尾入队
    void enQueueRear(E element);

    // 从队首出队
    E deQueueFront();

    // 从队尾出队
    E deQueueRear();

    // 获取队列头元素
    E front();

    // 获取队列尾元素
    E rear();

    void clear();

    boolean isEmpty();

}
