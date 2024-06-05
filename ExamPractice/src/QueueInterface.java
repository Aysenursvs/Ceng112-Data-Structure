public interface QueueInterface<T> {
    public void enqueque(T newEntry);
    public T dequeue();
    public T getFront();
    public boolean isEmpty();
    public void clear();
}
