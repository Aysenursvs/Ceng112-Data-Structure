public class BalanceChecker {
    public boolean checkBalance(String expression) {
        ArrayStack<Character> openDelimeterStack = new ArrayStack<Character>(50);
        boolean balanced = true;
        int index = 0;
        char nextChar;
        while (balanced == true && (index < expression.length())) {
            nextChar = expression.charAt(index);
            switch (nextChar) {
                case '(':
                case '[':
                case '{':
                    openDelimeterStack.push(nextChar);
                    break;
                case ')':
                case ']':
                case '}':
                    if (openDelimeterStack.isEmpty()) {
                        balanced = false;
                    } else {
                        char openDeli = openDelimeterStack.pop();
                        balanced = isPaired(openDeli, nextChar);
                    }
                    break;
                default:
                    break;
            }
            index++;
        }
        if(!openDelimeterStack.isEmpty()){
            balanced = false;
        }
        return balanced;

    }

    private boolean isPaired(char open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
    }
}

