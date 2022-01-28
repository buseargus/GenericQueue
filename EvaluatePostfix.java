package DataStructures.Stack;

public class EvaluatePostfix {

    private StackGeneric<String> stack;

    public EvaluatePostfix() {
        stack = new StackGeneric<>();
    }

    public int evaluatePostfixExpression(String postfix) {
        for(int i = 0; i < postfix.length(); i++) {
            String ch = Character.toString(postfix.charAt(i));
            if(!isOperator(ch)) {
                stack.push(ch);
            } else {
                int first = Integer.parseInt(stack.pop());
                int second = Integer.parseInt(stack.pop());
                stack.push(Integer.toString(operation(first, second, ch)));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private boolean isOperator(String s) {
        String[] operators = {"^", "+", "-", "*", "/"};
        for (String val : operators) {
            if(s.equals(val))
                return true;
        }
        return false;
    }

    private int operation(int a, int b, String c) {
        switch (c) {
            case "+" :
                return a + b;
            case "-" :
                return a - b;
            case "*" :
                return a * b;
            case "/" :
                return a / b;
            case "^" :
                return a ^ b;
            default:
                return 1;
        }
    }

}
