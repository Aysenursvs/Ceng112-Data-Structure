public class LinkedDeque <T> implements DequeInterface<T>{
    private DLNode firstNode;
    private DLNode lastNode;
    public LinkedDeque(){
        firstNode = null;
        lastNode = null;
    }
    // DLNode class
    private class DLNode{
        private T data;
        private DLNode next;
        private DLNode previous;
        private DLNode(DLNode previous,T data,DLNode next){
            this.setNext(next);
            this.setPrevious(previous);
            this.setData(data);
        }


        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public DLNode getNext() {
            return next;
        }

        public void setNext(DLNode next) {
            this.next = next;
        }

        public DLNode getPrevious() {
            return previous;
        }

        public void setPrevious(DLNode previous) {
            this.previous = previous;
        }
    }//end DLNoDE Class
    @Override
    public void addToBack(T newEntry) {
        DLNode newNode = new DLNode(lastNode, newEntry, null);
        if(isEmpty()){
            firstNode = newNode;
        }else{
            lastNode.setNext(newNode);
        }
        lastNode = newNode;
    }

    @Override
    public void addToFront(T newEntry) {
        DLNode newNode = new DLNode(null, newEntry, firstNode);
        if(isEmpty()){
            lastNode = newNode;
        }else{
            firstNode.setPrevious(newNode);
        }
        firstNode = newNode;
    }

    @Override
    public T removeFront() {
        T front = getFront();
        assert firstNode != null;
        firstNode = firstNode.getNext();
        if(firstNode== null){
            lastNode = null;
        }else{
            firstNode.setPrevious(null);
        }
        return front;
    }

    @Override
    public T removeBack() {
        T back = getBack();
        assert lastNode != null;
        lastNode = lastNode.getPrevious();
        if(lastNode== null){
            firstNode = null;
        }else{
            lastNode.setNext(null);
        }
        return back;
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
    public T getBack() {
        if(isEmpty()){
            throw new SecurityException("Queue is empty");
        }else{
            return lastNode.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        return firstNode == lastNode;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
    }
}
