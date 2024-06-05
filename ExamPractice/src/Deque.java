public class Deque <T> implements DequeInterface<T>{
    private T[] deque;
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    public Deque(){
        this(DEFAULT_CAPACITY);
    }
    public Deque(int capacity){
        checkCapacity(capacity);
        T[] tempQueue = (T[]) new Object[capacity+1];
        deque = tempQueue;
        frontIndex = 0;
        backIndex = capacity;
        initialized = true;
    }
    public void checkCapacity(int capacity){
        if(capacity < 0 || capacity > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a stack whose capacity exceeds allowed maximum of ." + MAX_CAPACITY);
        }
    }
    public void checkInitilization(){
        if(!initialized){
            throw new SecurityException("ArrayStack object is corrupt.");
        }
    }

    @Override
    public void addToBack(T newEntry) {
        checkInitilization();
        ensureCapacity();
        backIndex = (backIndex+1)% deque.length;
        deque[backIndex]=newEntry;
    }

    @Override
    public void addToFront(T newEntry) {
        checkInitilization();
        ensureCapacity();
        frontIndex = (frontIndex -1)%deque.length;
        deque[frontIndex]=newEntry;

    }

    @Override
    public T removeFront() {
        checkInitilization();
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else{
            T result = deque[frontIndex];
            deque[frontIndex]=null;
            frontIndex = (frontIndex+1)% deque.length;
            return result;
        }
    }

    @Override
    public T removeBack() {
        checkInitilization();
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else{
            T result = deque[backIndex];
            deque[backIndex]=null;
            backIndex = (backIndex-1)%deque.length;
            return result;
        }
    }

    @Override
    public T getFront() {
        checkInitilization();
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else{
            return deque[frontIndex];
        }
    }
    public T getBack(){
        checkInitilization();
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        else{
            return deque[backIndex];
        }
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == (backIndex+1)% deque.length;
    }

    @Override
    public void clear() {

    }
    private void ensureCapacity(){
        if(frontIndex == (backIndex+2)% deque.length){
            T[] oldQueue = deque;
            int oldSize = deque.length;
            int newSize = oldSize * 2;
            checkCapacity(newSize);
            T[] tempQueue = (T[]) new Object[newSize];
            deque = tempQueue;
            for(int i=0; i< newSize; i++){
                deque[i] = oldQueue[frontIndex];
                frontIndex = (frontIndex+1)%oldSize;
            }
            frontIndex = 0;
            backIndex = oldSize -2;
        }
    }
}
