package com.wang.exercises;

import java.util.ArrayList;
import java.util.Stack;

public class PostfixExpression {

    /**
     * 计算前缀表达式
     * <p>
     * 1、从左往右读，遇到数字添加到数字栈中numberStack
     * 2、遇到运算符则弹出numberStack中的栈顶元素和次栈顶元素，计算结果（次栈顶 op 栈顶），将计算完元素push至numberStack中
     * 3.重复2，直至表达式最右侧
     * 4。最终numberStack最后一个元素为计算结果
     *
     * @param list
     * @return
     */
    public static Double calPostExpression(ArrayList<String> list) {
        Stack<Double> numberStack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (isOperation(str.charAt(0))) {
                Double top = numberStack.pop();
                Double pre = numberStack.pop();
                Double result = cal(str, pre, top);
                numberStack.push(result);
            } else {
                numberStack.push(Double.parseDouble(str));
            }
        }
        return numberStack.peek();

    }

    public static Double cal(String operation, Double num1, Double num2) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                throw new RuntimeException("未知异常");
        }

    }


    /**
     * 中缀表达式转前缀表达式
     * <p>
     * (1) 初始化两个栈：运算符栈S1和储存中间结果的栈S2；
     * (2) 从左至右扫描中缀表达式；
     * (3) 遇到操作数时，将其压入S2；
     * (4) 遇到运算符时，比较其与S1栈顶运算符的优先级：
     * (4-1) 如果S1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * (4-2) 否则，若优先级比栈顶运算符的高，也将运算符压入S1（注意转换为前缀表达式时是优先级较高或相同，而这里则不包括相同的情况）；
     * (4-3) 否则，将S1栈顶的运算符弹出并压入到S2中，再次转到(4-1)与S1中新的栈顶运算符相比较；
     * (5) 遇到括号时：
     * (5-1) 如果是左括号“(”，则直接压入S1；
     * (5-2) 如果是右括号“)”，则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号为止，此时将这一对括号丢弃；
     * (6) 重复步骤(2)至(5)，直到表达式的最右边；
     * (7) 将S1中剩余的运算符依次弹出并压入S2；
     * (8) 依次弹出S2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式（转换为前缀表达式时不用逆序）。
     *
     * @return
     */
    public static ArrayList<String> postfixToPrefix(String expression) {

        Stack<Character> operation = new Stack<>();
        Stack<String> expressionStack = new Stack<>();

        int length = expression.length();

        for (int i = 0; i < length; i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                int lastIndex = parserNumber(expression, i);

                Double parseDouble = Double.parseDouble(expression.substring(i, lastIndex));
                i = lastIndex - 1;
                expressionStack.push(parseDouble.toString());
            } else if (isOperation(ch)) {
                while (!operation.isEmpty() && operation.peek() != '(' && priorityCompare(ch, operation.peek()) <= 0) {
                    expressionStack.push(operation.pop().toString());
                }
                operation.push(ch);
            } else if (ch == ')') {
                while (operation.peek() != '(') {
                    expressionStack.push(operation.pop().toString());
                }
                operation.pop();
            } else if (ch == '(') {
                operation.push(ch);
            } else if (ch == ' ') {

            } else {
                throw new RuntimeException("表达式格式错误");
            }
        }
        while (!operation.isEmpty()) {
            expressionStack.push(operation.pop().toString());
        }
        ArrayList<String> list = new ArrayList<>(expressionStack);
        return list;
    }

    public static boolean isOperation(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static boolean isBracket(char ch) {
        return ch == '(' || ch == ')';
    }

    public static int parserNumber(String expression, int index) {
        int count = 0;
        int lastIndex = index;
        for (int i = index; i < expression.length(); i++) {
            char charAt = expression.charAt(i);
            if (charAt == '.') {
                count++;
            }
            if ((isOperation(charAt) || isBracket(charAt))) {
                return i;
            }
            if (!Character.isDigit(charAt) && !isOperation(charAt) && !isBracket(charAt) && charAt != '.') {
                throw new RuntimeException("非法输入");
            }
            if (i == expression.length() - 1) {
                return i + 1;
            }
        }
        if (count > 1) {
            throw new RuntimeException("非法输入");
        }
        throw new RuntimeException("非法输入");

    }

    private static int priorityCompare(char op1, char op2) {
        switch (op1) {
            case '+':
            case '-':
                return (op2 == '*' || op2 == '/' ? -1 : 0);
            case '*':
            case '/':
                return (op2 == '+' || op2 == '-' ? 1 : 0);
        }
        return 1;
    }

}
