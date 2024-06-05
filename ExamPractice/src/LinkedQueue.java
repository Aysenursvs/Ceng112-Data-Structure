public class LinkedQueue <T> implements QueueInterface<T>{
    private Node firstNode;
    private Node lastNode;
    public LinkedQueue() {
        firstNode = null;
        lastNode = null;
    }
    private class Node{
        private T data;
        private Node next;
        private Node(T dataPortion) {
            this(dataPortion, null);
        }
        private Node(T dataPortion, Node nextNode){
            setData(dataPortion);
            setNext(nextNode);
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }// end Node class
    @Override
    public void enqueque(T newEntry) {
        Node newNode = new Node(newEntry, null);
        if(isEmpty()){
            firstNode = newNode;
        }else{
            lastNode.setNext(newNode);
        }
        lastNode = newNode;
    }

    @Override
    public T dequeue() {
        T frontData = firstNode.getData();
        assert firstNode != null;
        firstNode.setData(null);
        firstNode = firstNode.getNext();
        if(firstNode == null){
            lastNode = null;
        }
        return frontData;
    }

    @Override
    public T getFront() {
        if(isEmpty()){
            throw new SecurityException("Queue is empty");
        }else{
            return firstNode.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
    }



}
