import java.util.Arrays;

public class ArrayStack <T> implements StackInterface<T>{
    private T[] stack;
    private int topIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }
    public ArrayStack(int capacity){
        checkCapacity(capacity);
        T[] tempStack = (T[]) new Object[capacity];
        stack = tempStack;
        topIndex = -1;
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
    public void resizeArray(){
        if(topIndex == stack.length -1){
            int newLength = stack.length*2;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }
    @Override
    public void push(T newEntry) {
        checkInitilization();
        resizeArray();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    @Override
    public T pop() {
        checkInitilization();
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }else{
            T result = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return result;
        }
    }

    @Override
    public T peek() {
        checkInitilization();
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }else{
            return stack[topIndex];
        }
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public void clear() {
        checkInitilization();
        while(!isEmpty()){
            pop();
        }
    }
    public void displayElements(){
        checkInitilization();
        int tempTopIndex = topIndex;
        while(!isEmpty()){
            T a = peek();
            System.out.println(a);
            topIndex --;
        }
        topIndex = tempTopIndex;
    }
}
