public interface DequeInterface<T> {
    public void addToBack(T newEntry);
    public void addToFront(T newEntry);
    public T removeFront();
    public T removeBack();
    public T getFront();
    public T getBack();
    public boolean isEmpty();
    public void clear();
}
