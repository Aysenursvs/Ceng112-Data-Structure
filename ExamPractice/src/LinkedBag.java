public class LinkedBag <T> implements BagInterface<T>{
    private Node firstNode;
    private int numberOfEntries;
    public LinkedBag(){
        firstNode = null;
        numberOfEntries = 0;
    }
    //Node class
    private class Node {
        private T data;
        private LinkedBag.Node next;


        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    } // end Node class
    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        boolean result;
        if (numberOfEntries == 0) {
            assert firstNode == null;
            result = true;
        } else {
            assert firstNode != null;
            result = false;
        }
        return result;
    }

    private Node getReferanceTo(T anEntry){
        boolean found = false;
        Node cuurentNode = firstNode;
        while(!found && (cuurentNode != null)){
            if(anEntry.equals(cuurentNode.data)){
                found= true;
            }else{
                cuurentNode = cuurentNode.next;
            }
        }
        return cuurentNode;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferanceTo(anEntry);
        if(nodeN != null){
            nodeN.data = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            result = true;
        }
        return result;
    }

    @Override
    public T remove() { // Correct? Check
        T removedData = firstNode.data;
        firstNode = firstNode.next;
        numberOfEntries--;
        return removedData;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        int loopCounter = 0;
        Node currentNode = firstNode;
        while((loopCounter < numberOfEntries) && (currentNode != null)){
            if(anEntry.equals(currentNode.data)){
                frequency++;
            }
            loopCounter++;
            currentNode = currentNode.next;
        }
        return frequency;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node cuurentNode = firstNode;
        while(!found && (cuurentNode != null)){
            if(anEntry.equals(cuurentNode.data)){
                found= true;
            }else{
                cuurentNode = cuurentNode.next;
            }
        }
        return found;
    }

    @Override
    public void clear() {
        while (!isEmpty()){
            remove();
        }
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node cuurentNode = firstNode;
        while((index < numberOfEntries) && (cuurentNode != null)){
            result[index] = cuurentNode.data;
            index++;
            cuurentNode = cuurentNode.next;
        }
        return result;
    }
}
