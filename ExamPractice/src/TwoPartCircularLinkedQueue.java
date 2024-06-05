public class TwoPartCircularLinkedQueue <T> implements QueueInterface<T>{
    private Node queueNode;
    private Node freeNode;
    public TwoPartCircularLinkedQueue(){
        freeNode = new Node(null, null);
        freeNode.setNextNode(freeNode);
        queueNode = freeNode;
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
    private boolean isChainFull(){
        return queueNode == freeNode.getNextNode();
    }
    @Override
    public void enqueque(T newEntry) {
        freeNode.setData(newEntry);
        if(isChainFull()){
            Node newNode = new Node(null, freeNode.getNextNode());
            freeNode.setNextNode(newNode);
        }
        freeNode = freeNode.getNextNode();
    }

    @Override
    public T dequeue() {
        T front = getFront();
        assert !isEmpty();
        queueNode.setData(null);
        queueNode = queueNode.getNextNode();
        return front;
    }

    @Override
    public T getFront() {
        if(isEmpty()){
            throw new SecurityException("Queue is empty");
        }else{
            return queueNode.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        return queueNode == freeNode;
    }

    @Override
    public void clear() {
        queueNode = null;
        freeNode = null;
    }
}
