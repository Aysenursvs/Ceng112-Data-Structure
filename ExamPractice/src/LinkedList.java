public class LinkedList<T> implements ListInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedList(){
        initializeDataField();
    }

    @Override
    public void clear() {
        initializeDataField();
    }

    private void initializeDataField() {
        firstNode = null;
        numberOfEntries = 0;
    }

    private class Node {
        private T data;
        private Node next;


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


    }

    private Node getNodeAt(int givenPosition) {
        assert (firstNode != null) && (givenPosition > 1) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;
        for (int counter = 0; counter <= givenPosition; counter++) {
            currentNode = currentNode.getNextNode();
            assert currentNode != null;
        }
        return currentNode;
    }

    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode);
        }
        numberOfEntries++;
    }

    public void add(int givenPosition, T newEntry) {
        if (givenPosition > 0 && givenPosition <= numberOfEntries + 1) {
            Node newNode = new Node(newEntry);
            if (givenPosition == 1) {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else {
                Node beforeNode = getNodeAt(givenPosition - 1);
                Node afterNode = beforeNode.getNextNode();
                newNode.setNextNode(afterNode);
                beforeNode.setNextNode(newNode);
            }
            numberOfEntries++;
        } else {
            throw new IndexOutOfBoundsException("Illegal possition for adding operation");
        }
    }

    public T remove(int givenPosition) {
        assert isEmpty();
        T result = null;
        if ((givenPosition > 1) && (givenPosition <= numberOfEntries)) {
            if (givenPosition == 1) {
                result = firstNode.getData();
                firstNode = firstNode.getNextNode();
            } else {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
            }
            numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Illegal position");
        }
    }

    public T replace(int givenPosition, T newEntry) {
        assert isEmpty();
        if ((givenPosition > 0) && (givenPosition <= numberOfEntries)) {
            Node desiredNode = getNodeAt(givenPosition);
            T origanalData = desiredNode.getData();
            desiredNode.setData(newEntry);
            return origanalData;
        } else {
            throw new IndexOutOfBoundsException("Wrong Position");
        }
    }

    @Override
    public T getEntry(int givenPosition) {
        assert isEmpty();
        if((givenPosition>0) && (givenPosition<=numberOfEntries)){
            return getNodeAt(givenPosition).getData();
        }else{
            throw new IndexOutOfBoundsException("Wrong position");
        }
    }

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

    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node cuurentNode = firstNode;
        while(!found && (firstNode != null)){
            if(anEntry.equals(cuurentNode.getData())){
                found = true;
            }else{
                cuurentNode.setNextNode(cuurentNode);
            }
        }
        return found;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }
    public void display(){
        displayChain(firstNode);
    }
    private void displayChain(Node nodeOne){
        if(nodeOne != null){
            System.out.println(nodeOne.getData());
            displayChain(nodeOne.getNextNode());
        }
    }
    public void displayBackward(){
        displayChainBackward(firstNode);
    }
    private void displayChainBackward(Node nodeOne){
        if(nodeOne != null){
            displayChainBackward(nodeOne.getNextNode());
            System.out.println(nodeOne.getNextNode());
        }
    }
}
