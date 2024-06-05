import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Bag part
        System.out.println("******** BAG ************");
        ArrayBag<Integer> exampleBag = new ArrayBag<>(20);
        exampleBag.add(2);
        exampleBag.add(5);
        exampleBag.add(0);
        System.out.println("Inside of bag:");
        exampleBag.display();
        // Stack part
        System.out.println("******** STACK ************");
        BalanceChecker balance = new BalanceChecker();
        System.out.println(balance.checkBalance("()[{()}]"));
        ArrayStack<Integer> integerArrayStack = new ArrayStack<Integer>();
        integerArrayStack.push(5);
        integerArrayStack.push(56);
        integerArrayStack.push(1);
        integerArrayStack.push(7);
        integerArrayStack.peek();
        integerArrayStack.findElement(56);
        integerArrayStack.pop();
        InfixToPrefix infix = new InfixToPrefix();
        String postfix = infix.convertToPostfix("((5+2)(5*2+1))");
        System.out.println(postfix);
        // Queue part
        System.out.println("******** QUEUE ************");
        DequeInterface<Character> deque = new Deque<Character>();
        Scanner input = new Scanner(System.in);
        String expression = input.next(); //example expression: coo<mpuy<t5<er  "<" for deleting
        int index = 0;
        while (index < expression.length()) {
            char next = expression.charAt(index);
            if (next == '<') {
                deque.removeBack();
            } else {
                deque.addToBack(next);
            }
            index++;
        }
        while (!deque.isEmpty()) {
            System.out.print(deque.removeFront());
        }
        System.out.println();
        System.out.println("************ LIST ************************");
        // List part
        ArrayList<String> exampleList = new ArrayList<String>(10);
        exampleList.add("first");
        exampleList.add("second");
        exampleList.add("third");
        boolean empty = exampleList.isEmpty();
        System.out.println(empty);
        exampleList.displayListItem();
        System.out.println(exampleList.toArray());
        //Linked List Part
        LinkedList<String> linkedList = new LinkedList<String>(); // get node at methodunda bir sıkıntı var
        linkedList.add("a");
        linkedList.add("b");
        linkedList.display();
        System.out.println(linkedList.getEntry(1)); // null exception*/
        //Linked Queue
        LinkedQueue<String> exampleLinkedQueue = new LinkedQueue<String>();
        exampleLinkedQueue.enqueque("a");
        exampleLinkedQueue.enqueque("b");
        String front = exampleLinkedQueue.getFront();
        System.out.println(front);


    }
}

