import java.util.Arrays;

public class ArrayQueue<T> implements QueueInterface<T> {
    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    public ArrayQueue(){
        this(DEFAULT_CAPACITY);
    }
    public ArrayQueue(int capacity){
        checkCapacity(capacity);
        T[] tempQueue = (T[]) new Object[capacity+1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = capacity;
        initialized = true;
    }
    private void checkCapacity(int capacity){
        if(capacity < 0 || capacity > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a stack whose capacity exceeds allowed maximum of ." + MAX_CAPACITY);
        }
    }
    private void checkInitilization(){
        if(!initialized){
            throw new SecurityException("ArrayStack object is corrupt.");
        }
    }

    @Override
    public void enqueque(T newEntry) {
        checkInitilization();
        ensureCapacity();
        backIndex = (backIndex+1)% queue.length;
        queue[backIndex]=newEntry;
    }

    @Override
    public T dequeue() {
        checkInitilization();
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else{
            T result = queue[frontIndex];
            queue[frontIndex]=null;
            frontIndex = (frontIndex+1)% queue.length;
            return result;
        }
    }

    @Override
    public T getFront() {
        checkInitilization();
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else{
            return queue[frontIndex];
        }
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == (backIndex+1)% queue.length;
    }

    @Override
    public void clear() {
        while(!isEmpty()){
            dequeue();
        }
    }
    private void ensureCapacity(){
        if(frontIndex == (backIndex+2)% queue.length){
            T[] oldQueue = queue;
            int oldSize = queue.length;
            int newSize = oldSize * 2;
            checkCapacity(newSize);
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;
            for(int i=0; i< newSize; i++){
                queue[i] = oldQueue[frontIndex];
                frontIndex = (frontIndex+1)%oldSize;
            }
            frontIndex = 0;
            backIndex = oldSize -2;
        }
    }
}
