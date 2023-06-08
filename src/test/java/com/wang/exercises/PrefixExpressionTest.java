package com.wang.exercises;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PrefixExpressionTest  {


    @Test
    public void test01(){
        //expression = "1+3+(4+5)*8-6";

        //expression = "1+((2+3)*4)-5";
        //expression = "(3+4)*5-6";
        String expression = "(1+2)*3-(3+5)/7";
        List<String> list = PrefixExpression.infixToPrefix(expression);
        System.out.println("中缀表达式为：" + expression);
        System.out.print("前缀表达式为：");
        for (String str : list) {
            System.out.print(str);
        }
        System.out.println();
        //计算
        Double result = PrefixExpression.calPrefixExpression(list);
        System.out.println("计算结果为= " + result);
    }
}