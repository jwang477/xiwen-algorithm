package com.wang.exercises;// 前缀转中缀的注释已经相当的清晰了，所以这里就不在赘述了
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


public class CenterToLast {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        while(true) {
            System.out.println("请输入：");
            String str = sc.nextLine();
            System.out.println(toSuffix(str));
        }
    }

    static String operator = "+-*/";    // 用于判断是否是操作符
    // 用于判断优先级
    static HashMap<Character, Integer> priority = new HashMap<>();
    static {
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
    }


    private static String toSuffix(String str)  {  
        int len = str.length();  
        char c, tempChar;  
        ArrayDeque<Character> s1 = new ArrayDeque<Character>();  
        ArrayDeque<Double> s2 = new ArrayDeque<Double>();  
        Stack<Object> s3 = new Stack<>();
        double number;  
        int lastIndex = -1;  
        for (int i = 0; i < len; ++i) {
            c = str.charAt(i);  
            if (Character.isDigit(c)) {  
                lastIndex = readDouble(str, i);  
                number = Double.parseDouble(str.substring(i, lastIndex));  
                s2.push(number);  
                i = lastIndex - 1;  
                if ((int) number == number)  
                    s3.push((int)number);
                else  
                    s3.push(number);  
            }else if (c == '-' && i == 0) {
                lastIndex = readDouble(str, i + 1);  
                number = Double.parseDouble(str.substring(i, lastIndex));  
                s2.push(number);  
                i = lastIndex - 1;  
                if ((int) number == number)  
                    s3.push((int) number);  
                else  
                    s3.push(number);
            }else if (i > 0 && c == '-' &&
                    (str.charAt(i-1) == '(' 
                || operator.indexOf(str.charAt(i-1)) != -1)) {
                lastIndex = readDouble(str, i + 1);  
                number = Double.parseDouble(str.substring(i, lastIndex));  
                s2.push(number);  
                i = lastIndex - 1;  
                if ((int) number == number)  
                    s3.push((int)number); 
                else  
                    s3.push(number);
            }
            else if (operator.indexOf(c) != -1) {  
                while (!s1.isEmpty() && s1.peek() != '('  
                        && priority.get(c) < priority.get(s1.peek())) {  
                    System.out.print(s1.peek() + " ");  
                    double num1 = s2.pop();  
                    double num2 = s2.pop();
                    s3.push(s1.peek());
                    s2.push(calc(num2, num1, s1.pop()));  
                }  
                s1.push(c);  
            } else if (c == '(') {  
                s1.push(c);  
            } else if (c == ')') {  
                while ((tempChar = s1.pop()) != '(') {  
                    System.out.print(tempChar + " ");  
                    double num1 = s2.pop();  
                    double num2 = s2.pop(); 
                    s3.push(s1.peek());
                    s2.push(calc(num2, num1, tempChar));  
                    if (s1.isEmpty()) {  
                        return "出错";
                    }  
                }  
            } else if (c == ' ') {  
                    continue; 
            } else {  
                return "出错"; 
            }  
        }  
        while (!s1.isEmpty()) {  
            tempChar = s1.pop();  
            s3.push(tempChar);  
            double num1 = s2.pop();  
            double num2 = s2.pop();  
            s2.push(calc(num2, num1, tempChar));  
        }  
        double result = s2.pop();  
        if (!s2.isEmpty())  
            return "出错";
        System.out.print("后缀是：");
        while (!s3.isEmpty()) {
            System.out.print(s3.pop() + " ");
        }
        System.out.println();
        if ((int) result == result)  
            return String.valueOf(((int)result)); 
        else  
            return String.valueOf(result);  
    }

    /**
     * 获取是double值得最后一位索引
     */
    public static int readDouble(String str, int start) {
        int len = str.length();
        int dotIndex = -1;
        char ch;
        for (int i = start; i < len ; i++) {
            ch = str.charAt(i);
            if(ch == '.') {
                if(dotIndex != -1) 
                    return -1;
                else if(i == len - 1) 
                    return -1;
                else
                    dotIndex = i;
            } else if(!Character.isDigit(ch)) {
                if (dotIndex == -1 || i - dotIndex > 1)
                    return i;
                else
                    return -1;
            }
            else if(i == len - 1) {
                return len;
            }
        }
        return -1;
    }

    /**
     * 计算两个数的结果
     * 并返回
     */
    private static double calc (double num1, double num2, char op) 
        throws IllegalArgumentException{
        switch (op) {
        case '+':
            return num1 + num2;
        case '-':
            return num1-num2;
        case '*':
            return num1 * num2;
        case '/':
            if (num2 == 0) 
                throw new ArithmeticException("除数不能为0");
            return num1 / num2;
        default:
            return 0;
        }

    }
}
