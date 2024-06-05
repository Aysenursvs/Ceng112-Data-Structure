import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements BinaryTreeInterface<T> {
    private BinaryNode<T> root;

    public BinaryTree() {
        root = null;
    } // end default constructor

    public BinaryTree(T rootData) {
        root = new BinaryNode<>(rootData);
    } // end constructor

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        privateSetTree(rootData, leftTree, rightTree);
    } // end constructor

    public void setTree(T rootData) {
        root = new BinaryNode<>(rootData);
    } // end setTree

    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        privateSetTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
    } // end setTree
    private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        root = new BinaryNode<T>(rootData);
        if (leftTree != null && !leftTree.isEmpty()){
            root.setLeftChild(leftTree.root.copy());
        }
        if (rightTree != null && !rightTree.isEmpty()){
            root.setRightChild(rightTree.root.copy());
        }
    }
    public T getRootData() {
        if (isEmpty())
            throw new SecurityException("Empty tree");
        else
            return root.getData();
    } // end getRootData

    public int getHeight() {
        return root.getHeight();
    } // end getHeight

    public int getNumberOfNodes() {
        return root.getNumberOfNodes();
    } // end getNumberOfNodes

    public boolean isEmpty() {
        return root == null;
    } // end isEmpty

    public void clear() {
        root = null;
    } // end clear

    protected void setRootData(T rootData) {
        root.setData(rootData);
    } // end setRootData

    protected void setRootNode(BinaryNode<T> rootNode) {
        root = rootNode;
    } // end setRootNode

    protected BinaryNode<T> getRootNode() {
        return root;
    } // end getRootNode


    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    } // end getPreorderIterator

    public Iterator<T> getPostorderIterator() {
        return new PostorderIterator();
    } // end getPostorderIterator

    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    } // end getInorderIterator

    public Iterator<T> getLevelOrderIterator() {
        return new LevelOrderIterator();
    } // end getLevelOrderIterator




    // ************ INORDER TRAVERSAL ************
    // recursive ınorder traversal
    public void inorderTraverse() {
        inorderTraverse(root);
    }

    private void inorderTraverse(BinaryNode<T> node) {
        if (node != null) {
            inorderTraverse(node.getLeftChild());
            System.out.println(node.getData());
            inorderTraverse(node.getRightChild());
        }
    }

    // iterative inorder traversal
    public void iterativeInorderTraverse() {
        StackInterface<BinaryNode<T>> nodeStack = new ArrayStack<>();
        BinaryNode<T> currentNode = root;

        while (!nodeStack.isEmpty() || (currentNode != null)) {
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            } // end while

            if (!nodeStack.isEmpty()) {
                BinaryNode<T> nextNode = nodeStack.pop();
                System.out.println(nextNode.getData());
                currentNode = nextNode.getRightChild();
            } // end if
        } // end while
    } // end iterativeInorderTraverse

    // iterator for ınorder traversal
    private class InorderIterator implements Iterator<T> {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public InorderIterator() {
            nodeStack = new ArrayStack<>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next() {
            BinaryNode<T> nextNode = null;

            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            } // end while
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                currentNode = nextNode.getRightChild();
            } else
                throw new NoSuchElementException();

            return nextNode.getData();
        } // end next

        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove
    } // end InorderIterator

    // ************ PREORDER TRAVERSAL ************
    // recursive preorder traversal
    public void preorderTraverse() {
        preorderTraverse(root);
    }

    private void preorderTraverse(BinaryNode<T> node) {
        if (node != null) {
            System.out.println(node.getData());
            preorderTraverse(node.getLeftChild());
            preorderTraverse(node.getRightChild());
        }
    }

    // iterative preorder traversal
    public void iterativePreorderTraverse() {
        StackInterface<BinaryNode<T>> nodeStack = new ArrayStack<>();
        BinaryNode<T> currentNode = root;
        while (!nodeStack.isEmpty() || (currentNode != null)) {
            if (currentNode != null) {
                nodeStack.push(currentNode);
            }
            if (!nodeStack.isEmpty()) {
                BinaryNode<T> nextNode = nodeStack.pop();
                assert nextNode != null;
                //System.out.println(nextNode.getData());
                currentNode = nextNode.getRightChild();
                if (currentNode != null) {
                    nodeStack.push(currentNode);
                }
                currentNode = nextNode.getLeftChild();
            }
        }// end while
    } // end iterativePreorderTraverse

    // iterator for preorder traversal
    private class PreorderIterator implements Iterator<T> {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public PreorderIterator() {
            nodeStack = new ArrayStack<>();
            currentNode = root;
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        @Override
        public T next() {
            BinaryNode<T> nextNode = null;
            if (currentNode != null) {
                nodeStack.push(currentNode);
            }
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                currentNode = nextNode.getRightChild();
                if (currentNode != null) {
                    nodeStack.push(currentNode);
                }
                currentNode = nextNode.getLeftChild();

            } else {
                throw new NoSuchElementException();
            }
            return nextNode.getData();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    // ************ POSTORDER TRAVERSAL ************
    // recursive postorder traversal
    public void postorderTraverse() {
        postorderTraverse(root);
    }

    private void postorderTraverse(BinaryNode<T> node) {
        if (node != null) {
            postorderTraverse(node.getLeftChild());
            postorderTraverse(node.getRightChild());
            System.out.println(node.getData());
        }
    }

    // iterative postorder traversal
    public void iterativePostorderTraverse() {
        StackInterface<BinaryNode<T>> nodeStack = new ArrayStack<>();
        BinaryNode<T> currentNode = root;
        BinaryNode<T> nextNode = null;
        BinaryNode<T> previousNode = null;
        nodeStack.push(currentNode);
        while (!nodeStack.isEmpty() || (currentNode != null)) {
            currentNode = nodeStack.peek();
            if (previousNode == null || previousNode.getLeftChild() == currentNode || previousNode.getRightChild() == currentNode) {
                if (currentNode.getLeftChild() != null) {
                    nodeStack.push(currentNode.getLeftChild());
                } else if (currentNode.getRightChild() != null) {
                    nodeStack.push(currentNode.getRightChild());
                } else {
                    nextNode = nodeStack.pop();
                    System.out.println(nextNode.getData());
                }
            } else if (currentNode.getLeftChild() == previousNode) {
                if (currentNode.getRightChild() != null) {
                    nodeStack.push(currentNode.getRightChild());
                } else {
                    nextNode = nodeStack.pop();
                    System.out.println(nextNode.getData());
                }
            } else if (currentNode.getRightChild() == previousNode) {
                nextNode = nodeStack.pop();
                System.out.println(nextNode.getData());
            }
            previousNode = currentNode;
        }
    }
    // iterator for postorder traversal
    private class PostorderIterator implements Iterator<T>{
        StackInterface<BinaryNode<T>> nodeStack;
        BinaryNode<T> currentNode;
        BinaryNode<T> previousNode;
        public PostorderIterator(){
            nodeStack = new ArrayStack<>();
            currentNode = root;
            previousNode = null;
            nodeStack.push(currentNode);
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        @Override
        public T next() {
            BinaryNode<T> nextNode = null;
            currentNode = nodeStack.peek();
            if (previousNode == null || previousNode.getLeftChild() == currentNode || previousNode.getRightChild() == currentNode) {
                if (currentNode.getLeftChild() != null) {
                    nodeStack.push(currentNode.getLeftChild());
                } else if (currentNode.getRightChild() != null) {
                    nodeStack.push(currentNode.getRightChild());
                } else {
                    nextNode = nodeStack.pop();
                }
            } else if (currentNode.getLeftChild() == previousNode) {
                if (currentNode.getRightChild() != null) {
                    nodeStack.push(currentNode.getRightChild());
                } else {
                    nextNode = nodeStack.pop();
                }
            } else if (currentNode.getRightChild() == previousNode) {
                nextNode = nodeStack.pop();
            }
            previousNode = currentNode;
            return nextNode.getData();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    // ************ LEVEL ORDER TRAVERSAL ************
    // recursive level order traversal
    public void levelOrderTraverse() {
        int h = getHeight();
        for (int i = 1; i <= h; i++) {
            printGivenLevel(root, i);
        }
    }
    private void printGivenLevel(BinaryNode<T> node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.println(node.getData());
        } else if (level > 1) {
            printGivenLevel(node.getLeftChild(), level - 1);
            printGivenLevel(node.getRightChild(), level - 1);
        }
    }
    //iterative level order traversal
    public void iterativeLevelOrderTraverse(){
        QueueInterface<BinaryNode<T>> nodeQueue = new LinkedQueue<>();
        BinaryNode<T> currentNode = root;
        BinaryNode<T> previousNode = null;
        while(!nodeQueue.isEmpty() || currentNode != null){
            if(currentNode != null){
                nodeQueue.enqueue(currentNode);
            }if(previousNode != null && previousNode.getRightChild() != null){
                nodeQueue.enqueue(previousNode.getRightChild());
            }if(!nodeQueue.isEmpty()){
                BinaryNode<T> nextNode = nodeQueue.dequeue();
                assert nextNode != null;
                System.out.println(nextNode.getData());
                previousNode = nextNode;
                currentNode = nextNode.getLeftChild();
            }
        }
    }
    // iterator for level order traversal
    private class LevelOrderIterator implements Iterator<T>{
        QueueInterface<BinaryNode<T>> nodeQueue;
        BinaryNode<T> currentNode;
        BinaryNode<T> previousNode;
        public LevelOrderIterator(){
            nodeQueue = new LinkedQueue<>();
            currentNode = root;
            previousNode = null;
            nodeQueue.enqueue(currentNode);
        }
        @Override
        public boolean hasNext() {
            return !nodeQueue.isEmpty() || (currentNode != null);
        }
        @Override
        public T next() {
            BinaryNode<T> nextNode = null;
            currentNode = nodeQueue.dequeue();
            if(previousNode != null && previousNode.getRightChild() != null){
                nodeQueue.enqueue(previousNode.getRightChild());
            }if(currentNode != null){
                nodeQueue.enqueue(currentNode);
            }if(!nodeQueue.isEmpty()){
                nextNode = nodeQueue.dequeue();
                assert nextNode != null;
                previousNode = nextNode;
            }else{
                throw new NoSuchElementException();
            }
            return nextNode.getData();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

}
