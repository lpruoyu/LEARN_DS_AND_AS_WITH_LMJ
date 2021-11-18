package taught_by_mjlmj.stepone.interfaces;

public abstract class AbstractList<E> implements List<E> {

    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }

    @Override
    public boolean remove(E element) {
        remove(indexOf(element));
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            arrayIndexOutOfBoundsException(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            arrayIndexOutOfBoundsException(index);
        }
    }

    protected void arrayIndexOutOfBoundsException(int index) {
        throw new ArrayIndexOutOfBoundsException("index : " + index + " size : " + size);
    }

}
