package com.wang.exercises;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class CenterToFront {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入：");
            String s = sc.nextLine();
            String result = toFront(s);
            if (result != "出错") {
                System.out.println("结果是：" + result);
            }
            else {
                System.out.println(s);
            }
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
        priority.put(')', 0);
    }

    public static String toFront (String str) {
        ArrayDeque<Character> s1 = new ArrayDeque<>();  // 用于储存运算符
        ArrayDeque<Double> s2 = new ArrayDeque<>();     // 用于储存数字
        // 这里的这个栈不使用ArrayDeque是
        // 因为当ArrayDeque存入Object元素时,
        // 会将同一种类型的元素放在一块，这是一个坑点
        Stack<Object> s3 = new Stack<>();
        char ch, temp;      // ch储存每次遍历时，temp后面用于暂时储存字符，后面会用到
        // 用于储存数字的索引，从右向左遍历是frontDouble存储的是数字
        int frontDouble = -1;
        int len = str.length(); // 用于储存字符串的字符串的长度
        for (int i = len - 1; i >= 0; i--) {
            ch = str.charAt(i); // charAt()方法可以返回字符串索引处的字符

            if (Character.isDigit(ch)) {    // Character的isDigit()可以用来判断数字是否是字符
                frontDouble = readFrontDouble(str, i);  // 该方法用于算该数字从索引处到往前遍历找到第一个非数字的索引
                if (frontDouble == -1) {
                    return "出错";
                }
                // String类的substring(int a, int b)用于返回该字符串从a到b-1处的一个子字符串
                // Double.parseDouble(String str) 将一个自负串解析成一个double型数字
                double d = Double.parseDouble(str.substring(frontDouble, i + 1));
                if ((int)d == d)
                    s3.add(String.valueOf((int)d));
                else
                    s3.add(String.valueOf(d));
                s2.push(d);
                i = frontDouble;
            }
            /**
             * 查看ch是否是运算符
             * 若是运算符则与s1栈顶的运算符作比较
             *      1. 若栈顶元素为空，则将ch压入栈s1
             *      2. 若栈顶元素不为空，且ch的优先级比s1栈顶优先级高
             *          则将ch压入栈s1
             *      3. 若栈顶元素不为空，且ch的优先级比s1栈顶优先级低
             *          则从栈s2中弹出两个数、从栈s1中弹出一个运算符，
             *          然后做运算，将元素所得的结果压入栈s2
             */
            else if (operator.indexOf(ch) != -1) {
                while (!s1.isEmpty() && s1.peek() != ')'
                        && priority.get(ch) < priority.get(s1.peek())) {
                    double d1 = s2.pop();
                    double d2 = s2.pop();
                    s3.push(s1.peek().toString());
                    s2.push(cal(d1, d2, s1.pop()));
                }
                s1.push(ch);
            }
            // 如遇到右括号，直接压入栈
            else if (ch == ')')
                s1.push(ch);
            /**
             * 如果遇到左括号，并且s1栈顶元素不是右括号
             * 则从s2栈顶弹出两个数字，从s1栈顶弹出一个运算符
             * 做运算，将结果存入栈s2
             * 循环上述过程知道s1栈顶元素是左括号，然后将左括号弹出
             *
             */
            else if (ch == '(') {
                while (s1.peek() != ')') {
                    double d1 = s2.pop();
                    double d2 = s2.pop();
                    s3.push(s1.peek().toString());
                    s2.push(cal(d1, d2, s1.pop()));
                    // 如果没遇到左括号，但s1栈已经是空的了，那么肯定出错了
                    if (s1.isEmpty()) {
                        return "出错";
                    }
                }
                s1.pop();
            }
            // 忽略掉空格
            else if (ch == ' ') {
                continue;
            }
            // 有其他字符肯定出错
            else {
                return "出错";
            }
        }
        /**
         * 字符串遍历完成后若s1栈不为空（一般来说坑定不为空）
         * 则从栈s1中弹出一个符号，从s2栈顶弹出两个数字做运算
         * 后压入栈s2，直到栈s1为空
         */

        while (!s1.isEmpty()) {
            double d1 = s2.pop();
            double d2 = s2.pop();
            s3.push(s1.peek().toString());
            double d3 = cal(d1, d2, s1.pop());
            s2.push(d3);
        }
        System.out.print("前缀是：");
        while(!s3.isEmpty()) {
            System.out.print(s3.pop() + " ");
        }
        System.out.println();
        // 若最后栈s2中还有超过一个元素，则证明出错了
        double result = s2.pop();
        if (!s2.isEmpty())
            return "出错";
        if ((int) result == result)
            return String.valueOf((int)result);
        else{
            return String.valueOf(result);
        }

    }
    /**
    * 此方法用于计算的d1 op d2 的结果
    */ 
    private static double cal(double d1, double d2, char op)
            throws ArithmeticException{
        switch (op) {
            case '+':
                return d1 + d2;
            case '-':
                return d1 - d2;
            case '*':
                return d1 * d2;
            case '/':
                if (d1 == 0) {
                    return 1;
                }
                return d1 / d2;
        }

        return 1;
    }
    /**
    * 这是一个读取数字位置的方法
    * 该方法可以从右向左的读取一个数字，然后返回该数字在字符串中开始的下标
    */ 
    private static int readFrontDouble(String str, int start) {
        int flag = -1;    // 用于记录小数点
        char ch;          // 用于记录每次遍历的字符
        for (int i = start; i >= 0; i--) {
            ch = str.charAt(i);
            if (ch == '.') {      //如果第一次出现小数点，则记录小数点位置，如果不是那么肯定出错
                if (flag != -1) {
                    return -1;
                }
                else {
                    flag = i;
                }
            // 如果该字符是减号，若该字符是第一位（i == 0），则该减号是负号，或者如果该字符的前一个字符不是数字，证明也是负号
            } else if (ch == '-' && ((i > 0
                    && !Character.isDigit((str.charAt(i-1))))
                    || i == 0)) {
                return i;
                // 如果是非数字的肯定该数字已经找到了
            }else if (!Character.isDigit(ch))
                return i + 1;
            else if (i == 0) {
                return 0;
            }
        }
        return -1;
    }
}
