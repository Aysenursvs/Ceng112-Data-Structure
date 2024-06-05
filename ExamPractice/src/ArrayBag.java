import java.util.Arrays;

public class ArrayBag <T> implements BagInterface<T>{
    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private boolean initialized = false;
    private static final int MAX_CAPACITY =10000;
    public ArrayBag(){
        this(DEFAULT_CAPACITY);
    }
    public ArrayBag(int newCpacity){
        checkCpacity(newCpacity);
        T[] tempBag = (T[]) new Object[newCpacity];
        bag = tempBag;
        numberOfEntries = 0;
        initialized = true;
    }
    private void checkInitilization(){
        if(!initialized){
            throw new SecurityException("Not initialized");
        }
    }
    private void checkCpacity(int capacity){
        if(capacity<0 || capacity > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum of" + MAX_CAPACITY);
        }
    }
    private void doubleCapacity() {
        int newLength = 2*bag.length;
        checkCpacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    }

    @Override
    public boolean add(T newEntry) {
        checkInitilization();
        boolean result = false;
        if(isFull()){
            doubleCapacity();
        }else{
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
            result = true;
        }
        return result;
    }

    @Override
    public int getCurrentSize() {
        checkInitilization();
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }


    private boolean isFull() {
        if (numberOfEntries == bag.length){
            return true;
        }else{
            return false;
        }
    }


    private T removeByIndex(int index) {
        checkInitilization();
        T result = null;
        if(!isEmpty() && (index >= 0)){
            result = bag[index];
            bag[index] = bag[numberOfEntries -1];
            bag[numberOfEntries-1] = null;
            numberOfEntries -= 1;
        }
        return result;
    }

    @Override
    public boolean remove(T anEntry) {
        checkInitilization();
        int index = getIndexOf(anEntry);
        T removed = removeByIndex(index);
        return removed == anEntry;
    }

    @Override
    public T remove() {
        checkInitilization();
        T result = null;
        if(numberOfEntries > 0){
            result = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        checkInitilization();
        int counter = 0;
        if(!isEmpty()){
            for(int i = 0; i <= numberOfEntries; i++){
                if(anEntry == bag[i]){
                    counter ++;
                }
            }
        }
        return counter;
    }


    private int getIndexOf(Object anEntry) {
        checkInitilization();
        int where = -1;
        boolean found = false;
        int index = 0;
        while(!found && (index < numberOfEntries)) {
            if(anEntry == bag[index]) {
                found = true;
                where = index;
            }
            index ++;

        }
        return where;
    }

    @Override
    public boolean contains(Object anEntry) {
        checkInitilization();
        return getIndexOf(anEntry)> -1;
    }

    @Override
    public void clear() {
        checkInitilization();
        while(!isEmpty()){
            remove();
        }
    }
    public void displayItems() {
        for(int i = 0; i < numberOfEntries; i++){
            System.out.println(bag[i]);
        }

    }
    public T[] toArray() {

        T[] result = (T[])new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        }
        return result;

    }
    public void display(){
        displayArray(0, numberOfEntries-1);
    }
    private void displayArray(int first, int last){
        System.out.println(bag[first]);
        if(first < last){
            displayArray(first+1, last);
        }
    }
    
}
