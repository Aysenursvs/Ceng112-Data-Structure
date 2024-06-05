public class InfixToPrefix {
    public String convertToPostfix(String expression) {
        ArrayStack<Character> operators = new ArrayStack<Character>();
        String postfix = "";
        int index = 0;
        while (index < expression.length()) {
            char nextChar = expression.charAt(index);
            switch (nextChar) {
                case '^':
                    operators.push(nextChar);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!operators.isEmpty()&& predence(nextChar)<predence(operators.peek())){
                        postfix += operators.pop();
                    }
                    operators.push(nextChar);
                    break;
                case'(':
                    operators.push(nextChar);
                    break;
                case')':
                    char topOperator = operators.pop();
                    while(topOperator!='('){
                        postfix += topOperator;
                        topOperator = operators.pop();
                    }
                    break;
                default:
                    postfix += nextChar;
                    break;
            }
            index++;
        }
        while(!operators.isEmpty()){
            postfix += operators.pop();
        }
        return postfix;
    }
    private static int predence(char character){
        int predence = 0;
        switch (character){
            case '^':
                predence = 3;
                break;
            case'*': case'/':
                predence = 2;
                break;
            case '+': case'-':
                predence = 1;
                break;
            default:
                predence = 0;


        }
        return predence;}

    }
