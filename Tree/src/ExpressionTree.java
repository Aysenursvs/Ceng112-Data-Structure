public class ExpressionTree extends BinaryTree<String> implements ExpressionTreeInterface{
    public ExpressionTree(){
    }

    @Override
    public double evaluate() {
        return evaluate(getRootNode());
    }
    private double evaluate(BinaryNode<String> rootNode){
        double result;
        if(rootNode == null){
            result = 0;
        }else if(rootNode.isLeaf()){
            String variable = rootNode.getData();
            result = getValueOf(variable);
        }else{
            double firstOperand = evaluate(rootNode.getLeftChild());
            double secondOperand = evaluate(rootNode.getRightChild());
            String operator = rootNode.getData();
            result = compute(operator, firstOperand,secondOperand);
        }
        return result;
    }
    private double getValueOf(String variable){
        return Double.parseDouble(variable);
    }
    private double compute(String operator, double firstOperand, double secondOperand){
        double result = 0;
        switch(operator.charAt(0))
        {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                result = firstOperand / secondOperand;
                break;
            default:
                System.out.println("Error in Identification Character.");
        } // end switch

        return result;
    }
}
