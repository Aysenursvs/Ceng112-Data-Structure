import java.util.Arrays;

public class ArrayList <T> implements ListInterface<T>{
    private T[] list;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    private boolean initilalized;

    public ArrayList(int initialCpacity){
        if(initialCpacity < DEFAULT_CAPACITY){
            initialCpacity = DEFAULT_CAPACITY;
        }else{
            checkCpacity(initialCpacity);
        }
        //@SuppressWarnings("Unchecked");
        T[] tempList = (T[]) new Object[initialCpacity+1];
        list = tempList;
        numberOfEntries = 0;
        initilalized = true;
    }
    public void checkCpacity(int capacity){
        if(capacity < 0 || capacity > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a list whose capacity exceeds allowed maximum of ."+MAX_CAPACITY);
        }
    }
    public void checkInitilization(){
        if(!initilalized){
            throw new SecurityException("ArrayList object is corrupt.");
        }
    }
    private void ensureCpacity(){
        int capacity = list.length-1;
        if(numberOfEntries >= capacity){
            int newCapacity = 2*capacity;
            checkCpacity(newCapacity);
            list = Arrays.copyOf(list, newCapacity+1);
        }
    }
    @Override
    public void add(T newEntry) {
        checkInitilization();
        ensureCpacity();
        list[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
    }

    @Override
    public void add(int newPosition, T newEntry) {
        checkInitilization();
        if((newPosition>1) && (newPosition <= numberOfEntries + 1)){
            if(newPosition <= numberOfEntries){
                makeRoom(newPosition);
            }
            list[newPosition] = newEntry;
            numberOfEntries++;
            ensureCpacity();
        }else{
            throw new IndexOutOfBoundsException("Given position is wrong");
        }

    }
    private void makeRoom(int newPosition){
        assert (newPosition>1) && (newPosition < numberOfEntries+1);
        int newIndex = newPosition;
        int lastIndex = numberOfEntries;
        for(int index = lastIndex;index >= newIndex;index--){
            list[index+1]=list[index];
        }

    }


    public T remove() {
        checkInitilization();
        if(!isEmpty()){
            T result = list[numberOfEntries];
            list[numberOfEntries] = null;
            numberOfEntries--;
            return result;
        }else {
            throw new RuntimeException("List is empty");
        }
    }

    @Override
    public T remove(int givenPosition) {
        checkInitilization();
        assert isEmpty();
        if((givenPosition > 0) && (givenPosition <= numberOfEntries)){
            T result = list[givenPosition];
            if(givenPosition <= numberOfEntries){
                removeGap(givenPosition);
            }
            numberOfEntries--;
            return result;
        }else{
            throw new IndexOutOfBoundsException("Wrong position.");
        }
    }
    private void removeGap(int givenPosition){
        assert (givenPosition > 0) && (givenPosition <= numberOfEntries);
        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;
        for(int index = removedIndex; index <= lastIndex; index++){
            list[index] = list[index+1];
        }
    }

    @Override
    public void clear() {
        checkInitilization();
        if(!isEmpty()){
            while(numberOfEntries != 0){
                remove();
            }
        }
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        checkInitilization();
        assert (!isEmpty());
        if((givenPosition > 0) && (givenPosition <= numberOfEntries)){
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        }else{
            throw new IndexOutOfBoundsException("Wrong position.");
        }
    }

    @Override
    public T getEntry(int givenPosition) {
        checkInitilization();
        assert !isEmpty();
        if((givenPosition > 0) && (givenPosition <= numberOfEntries)){
            T result = list[givenPosition];
            return result;
        }else{
            throw new IndexOutOfBoundsException("Wrong position");
        }
    }

    @Override
    public T[] toArray() {
        checkInitilization();
        //@SuppressWarnings("Unchecked");
        T[] result = (T[]) new Object[numberOfEntries];
        for(int index = 0; index < numberOfEntries; index++){
            result[index] = list[index +1];
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        checkInitilization();
        boolean found = false;
        int index = 1;
        while((!found) && index <= numberOfEntries){
            if(list[index] == anEntry){
                found = true;
            }
            index++;
        }
        return found;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    public void displayListItem(){
        checkInitilization();
        assert !isEmpty();
        int index=1;
        while(index <= numberOfEntries){
           System.out.println(index + ". element: " + list[index]);
           index++;
        }
    }

}
