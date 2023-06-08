package com.wang.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式转前缀表达式
 */
public class PrefixExpression {


    /**
     * 计算前缀表达式
     * <p>
     * 1、从右往左读，遇到数字添加到数字栈中numberStack
     * 2、遇到运算符则弹出numberStack中的栈顶元素和次栈顶元素，计算结果（栈顶 op 次栈顶），将计算完元素push至numberStack中
     * 3.重复2，直至表达式最左侧
     * 4。最终numberStack最后一个元素为计算结果
     *
     * @param list
     * @return
     */
    public static Double calPrefixExpression(List<String> list) {
        Stack<Double> numberStack = new Stack<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            String str = list.get(i);
            if (isOperation(str.charAt(0))) {
                Double top = numberStack.pop();
                Double pre = numberStack.pop();
                Double result = cal(str, top, pre);
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
     * 1.从右往左扫描 ，新建两个栈 operation存放操作符，expressionStack存放表达式
     * 2.若为数字，直接push到expressionStack中其他见下
     * 3.若为运算符
     * (1)若operation为空或栈顶为）时，直接push到operation
     * (2)若栈顶操作符优先级低于或等于当前运算符，直接push到operation
     * (3)若栈顶操作符优先级高于当前运算符，弹出operation栈顶push到expressionStack中（重复 3，直到operation栈为空或栈顶元素优先级低于当前运算符）
     * 4.若为括号
     * (1)若为）直接push到operation中
     * (2)若为(operation依次弹出元素push到expressionStack中，直到遇到)为止
     * 5.重复2-4操作，直至表达式最左侧，
     * 6.将operation栈中元素一次push至expressionStack中
     * 7.依次弹出expressionStack元素，这就是前缀表达式
     *
     * @return
     */
    public static List<String> infixToPrefix(String expression) {

        Stack<Character> operation = new Stack<>();
        Stack<String> expressionStack = new Stack<>();

        int length = expression.length();

        for (int i = length - 1; i >= 0; i--) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                int lastIndex = parserNumber(expression, i);

                Double parseDouble = Double.parseDouble(expression.substring(lastIndex, i + 1));
                i = lastIndex;
                expressionStack.push(parseDouble.toString());
            } else if (isOperation(ch)) {
                while (!operation.isEmpty() && operation.peek() != ')' && priorityCompare(ch, operation.peek()) < 0) {
                    expressionStack.push(operation.pop().toString());
                }
                operation.push(ch);
            } else if (ch == '(') {
                while (operation.peek() != ')') {
                    expressionStack.push(operation.pop().toString());
                }
                operation.pop();
            } else if (ch == ')') {
                operation.push(ch);
            } else if (ch == ' ') {

            } else {
                throw new RuntimeException("表达式格式错误");
            }
        }
        while (!operation.isEmpty()) {
            expressionStack.push(operation.pop().toString());
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = expressionStack.size() - 1; i >= 0; i--) {
            list.add(expressionStack.get(i));
        }
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
        for (int i = index; i >= 0; i--) {
            char charAt = expression.charAt(i);
            if (charAt == '.') {
                count++;
            }
            if ((isOperation(charAt) || isBracket(charAt))) {
                return i + 1;
            }
            if (!Character.isDigit(charAt) && !isOperation(charAt) && !isBracket(charAt) && charAt != '.') {
                throw new RuntimeException("非法输入");
            }
            if (i == 0) {
                return 0;
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
            default:
                throw new RuntimeException("操作符异常");
        }
    }

}
