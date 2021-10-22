package taught_by_mjlmj.interfaces;

public interface List<E> {

    int ELEMENT_NOT_FOUND = -1;

    int size();

    boolean add(E element);

    void add(int index, E element);

    boolean remove(E element);

    E remove(int index);

    E get(int index);

    E set(int index, E element);

    int indexOf(E element);

    boolean isEmpty();

    boolean contains(E element);

    void clear();

}
