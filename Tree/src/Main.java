import java.util.Iterator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BinaryTreeInterface<String> dTree = new BinaryTree<>();
        dTree.setTree("D");
        BinaryTreeInterface<String> fTree = new BinaryTree<>();
        fTree.setTree("F");
        BinaryTreeInterface<String> gTree = new BinaryTree<>();
        gTree.setTree("G");
        BinaryTreeInterface<String> hTree = new BinaryTree<>();
        hTree.setTree("H");
        BinaryTreeInterface<String> emptyTree = new BinaryTree<>();
        BinaryTreeInterface<String> eTree = new BinaryTree<>();
        eTree.setTree("E", fTree, gTree);
        BinaryTreeInterface<String> bTree = new BinaryTree<>();
        bTree.setTree("B", dTree, eTree);
        BinaryTreeInterface<String> cTree = new BinaryTree<>();
        cTree.setTree("C", emptyTree, hTree);
        BinaryTreeInterface<String> aTree = new BinaryTree<>();
        aTree.setTree("A", bTree, cTree);
        System.out.println(aTree.getHeight());
        System.out.println("Preorder");
        Iterator<String> preorder = aTree.getPreorderIterator();
        while (preorder.hasNext()){
            System.out.println(preorder.next());
        }
        System.out.println("Inorder");
        Iterator<String> inorder = aTree.getInorderIterator();
        while (inorder.hasNext()){
            System.out.println(inorder.next());
        }
        System.out.println("Postorder");
        /*Iterator<String> postorder = aTree.getPostorderIterator();
        while (postorder.hasNext()){
            System.out.println(postorder.next());
        }*/
        System.out.println("Levelorder");
        /*Iterator<String> levelorder = aTree.getLevelOrderIterator();
        while (levelorder.hasNext()){
            System.out.println(levelorder.next());
        }*/
        System.out.println("**********************************************************");


    }
}