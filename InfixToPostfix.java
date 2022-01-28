package DataStructures.Stack;

public class InfixToPostfix {

    private StackGeneric<String> stack;

    public InfixToPostfix() {
        stack = new StackGeneric<>();
    }

    public String convertInfixToPostfix(String infix) {
        stack.push("("); infix += ")";
        String postfix = "";
        for(int i = 0; i < infix.length(); i++) {
            String ch = Character.toString(infix.charAt(i));
            if(isOperator(ch)){
                while(!stack.isEmpty() && isOperator(stack.peek())) {
                     if(precedence(ch) <= precedence(stack.peek())) {
                         postfix += stack.pop();
                     } else break;
                }
                stack.push(ch);
            } else if(ch.equals(")")) {
                while(!stack.isEmpty()) {
                    if(!stack.peek().equals("(")) {
                        postfix += stack.pop();
                    }
                    else {
                        stack.pop();
                        break;
                    }
                }
            }else if(ch.equals("(")) {
                stack.push(ch);
            }
            else {
                postfix += ch;
            }
        }
        return postfix;
    }

    private boolean isOperator(String s) {
        String[] operators = {"^", "+", "-", "*", "/"};
        for (String val : operators) {
            if(s.equals(val))
                return true;
        }
        return false;
    }

    private int precedence(String s) {
        switch (s) {
            case "^" :
                return 3;
            case "*":
                return 2;
            case "/" :
                return 2;
            case "+":
                return 1;
            case "-":
                return 1;
            default:
                return 0;
        }
    }
}
